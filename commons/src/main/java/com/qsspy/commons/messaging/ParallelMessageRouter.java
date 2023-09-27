package com.qsspy.commons.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * <h1>ParallelMessageRouter</h1>
 * <br/>
 * <p>
 *     Used for catching events and executing specified action based on event type.
 *     Processing is parallel. Level of parallelism is configured by underlying {@link ExecutorService}.
 *     If used along with spring-boot-cloud-binder-kafka offset commits will be executed only if whole batch is processed successfully.
 *     If error is thrown while executing batch, then whole batch will be processed again so independence is required.
 *     Backoff policy on error occurred should be configured on spring-boot-cloud configuration level.
 *     IMPORTANT NOTES:
 *     This router relies on message consumer binding with batching mode enabled.
 *     To enable it set in configuration: spring.cloud.stream.bindings."your-binding-name".consumer.batch-mode=true
 * </p>
 **/
@Slf4j
public class ParallelMessageRouter implements Consumer<Message<List<String>>> {

    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";

    private final ObjectMapper objectMapper;
    private final ExecutorService jobsExecutor;
    private final List<Case> cases = new LinkedList<>();
    private ScheduledExecutorService delayedJobsExecutor;
    private BiConsumer<String, Map<String, Object>> defaultCaseAction;
    private boolean isLoggingOnMessageReceived = false;

    private ParallelMessageRouter(
            final ObjectMapper objectMapper,
            final ExecutorService jobsExecutor
    ) {
        this.objectMapper = objectMapper;
        this.jobsExecutor = jobsExecutor;
    }

    /**
     * Returns new instance of ParallelMessageRouter.
     *
     * @param objectMapper mapper to be used for event serializing
     * @param executorService executor to be used to achieve parallelism
     * @return new instance of {@link ParallelMessageRouter}
     */
    public static ParallelMessageRouter of(
            final ObjectMapper objectMapper,
            final ExecutorService executorService
    ) {
        return new ParallelMessageRouter(objectMapper, executorService);
    }

    public static ParallelMessageRouter ofVirtualExecutor(
            final ObjectMapper objectMapper
    ) {
        return new ParallelMessageRouter(objectMapper, Executors.newVirtualThreadPerTaskExecutor());
    }

    /**
     * Adds action which will be executed when provided 'type' and 'version' matches ones from message.
     *
     * @param eventType type of the message
     * @param eventVersion version of the message
     * @param eventClass class of the message
     * @param action action to be executed for this type of message
     * @param <T> type of the event
     * @return this instance
     */
    public <T> ParallelMessageRouter addEventHandler(final String eventType, final String eventVersion, final Class<T> eventClass, final Consumer<T> action) {
        cases.add(new Case(eventType, eventVersion, (Class<Object>) eventClass, (Consumer<Object>) action, 0));
        return this;
    }

    public <T> ParallelMessageRouter addEventHandler(final String eventType, final int eventVersion, final Class<T> eventClass, final Consumer<T> action) {
        return addEventHandler(eventType, String.valueOf(eventVersion), eventClass, action);
    }

    /**
     * Adds action which is executed when provided 'type' and 'version' matches ones from message.
     * Action will be executed after specified time delay in milliseconds.
     *
     * @param eventType type of the message
     * @param eventVersion version of the message
     * @param eventClass class of the message
     * @param action action to be executed for this type of message
     * @param delayMilliseconds how much time to wait before action execution
     * @param <T> type of the event
     * @return this instance
     */
    public <T> ParallelMessageRouter addDelayedEventHandler(final String eventType, final String eventVersion, final Class<T> eventClass, final Consumer<T> action, final long delayMilliseconds) {
        if(delayedJobsExecutor == null) {
            throw new IllegalArgumentException("Delayed jobs executor must be set if you want to schedule delayed handlers. Use custom scheduled executor service or one of ParallelMessageRouter.ScheduledExecutor executors");
        }

        cases.add(new Case(eventType, eventVersion, (Class<Object>) eventClass, (Consumer<Object>) action, delayMilliseconds));
        return this;
    }

    public <T> ParallelMessageRouter addDelayedEventHandler(final String eventType, final int eventVersion, final Class<T> eventClass, final Consumer<T> action, final long delayMilliseconds) {
        return addDelayedEventHandler(eventType, String.valueOf(eventVersion), eventClass, action, delayMilliseconds);
    }

    /**
     * Adds action which is executed when any of provided cases does not match incoming message.
     *
     * @param action action to be executed
     * @return this instance
     */
    public ParallelMessageRouter addDefaultEventHandler(final BiConsumer<String, Map<String, Object>> action) {
        defaultCaseAction = action;
        return this;
    }

    /**
     * Setups scheduled executor service for delayed handlers' execution.
     *
     * @param executor scheduled executor service to be used
     * @return this instance
     */
    public ParallelMessageRouter setDelayedHandlerExecutor(final ScheduledExecutorService executor) {
        this.delayedJobsExecutor = executor;
        return this;
    }

    /**
     * Sets the flag which controls if message content should be logged after receiving message from channel.
     *
     * @return this instance
     */
    public ParallelMessageRouter enableLoggingOnMessageReceived() {
        isLoggingOnMessageReceived = true;
        return this;
    }

    @Override
    public void accept(final Message<List<String>> messagesList) {
        dispatch(messagesList);
    }

    private void dispatch(final Message<List<String>> messagesList) {

        final var rawMessages = messagesList.getPayload();
        log.debug("Started processing new batch of messages of size {}", rawMessages.size());

        final List<Future<?>> jobsToExecute = new LinkedList<>();

        for (int i = 0; i < rawMessages.size(); i++) {
            final var rawMessage = rawMessages.get(i);
            final var headers = extractMessageHeaders(messagesList, i);

            final var jobToExecute = dispatchSingleMessage(rawMessage, headers);
            if(jobToExecute != null) {
                jobsToExecute.add(jobToExecute);
            }
        }

        jobsToExecute.forEach(job -> {
            try {
                job.get();
            } catch (final InterruptedException exception) {
                log.warn("Thread has been interrupted", exception);
                Thread.currentThread().interrupt();
            } catch (final ExecutionException exception) {
                log.error("An error occurred while joining handler job", exception);
                throw new IllegalStateException(exception);
            }
        });

        log.debug("Batch of messages of size {} processed successfully", rawMessages.size());
    }

    private Map<String, Object> extractMessageHeaders(final Message<List<String>> messagesList, final int positionInBatch) {
        return Optional
                .ofNullable(messagesList.getHeaders().get(KafkaHeaders.BATCH_CONVERTED_HEADERS))
                .map(typelessHeaders -> (List<Map<String, Object>>) typelessHeaders)
                .map(headers -> headers.get(positionInBatch))
                .orElseThrow(() -> new IllegalArgumentException("Could not resolve message headers"));
    }

    @SneakyThrows
    @Nullable
    private Future<?> dispatchSingleMessage(final String rawMessage, final Map<String, Object> headers) {
        logIfEnabled(rawMessage);

        final String eventType = (String) headers.get(EVENT_TYPE_HEADER_NAME);
        final String eventVersion = resolveEventVersion(headers);

        for (final Case eventCase: cases) {

            if(eventCase.eventType.equals(eventType) && eventCase.eventVersion.equals(eventVersion)) {
                final Object event = objectMapper.readValue(rawMessage, eventCase.eventClass);

                if(eventCase.waitTimeMilliseconds > 0) {
                    return delayedJobsExecutor.schedule(
                            wrapIntoRunnable(eventCase.action, event), eventCase.waitTimeMilliseconds, TimeUnit.MILLISECONDS
                    );
                }

                return CompletableFuture.runAsync(wrapIntoRunnable(eventCase.action, event), jobsExecutor);
            }
        }

        if(nonNull(defaultCaseAction)) {
            defaultCaseAction.accept(rawMessage, headers);
        }

        return null;
    }

    private Runnable wrapIntoRunnable(final Consumer<Object> action, final Object event) {
        return () -> {
            try {
                action.accept(event);
            } catch (final Exception exception) {
                log.error("An error occurred while running handler for message: {}", event, exception);
                throw exception;
            }
        };
    }

    private String resolveEventVersion(final Map<String, Object> rawMessageHeaders) {
        final Object version = rawMessageHeaders.get(EVENT_VERSION_HEADER_NAME);

        if(isNull(version)) {
            return null;
        }

        return version.toString();
    }

    private void logIfEnabled(final String message) {
        if(isLoggingOnMessageReceived) {
            log.info("Received message : {}", message);
        }
    }

    private record Case(
            String eventType,
            String eventVersion,
            Class<Object> eventClass,
            Consumer<Object> action,
            long waitTimeMilliseconds
    ) { }
}