package com.qsspy.commons.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class SynchronousMessageRouter implements Consumer<Message<String>> {
    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";

    private final ObjectMapper objectMapper;

    private final List<Case> cases = new LinkedList<>();
    private Consumer<Message<String>> defaultCaseAction;
    private boolean isLoggingOnMessageReceived = false;

    private SynchronousMessageRouter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Returns new instance of SynchronousMessageRouter using provided {@link ObjectMapper}
     *
     * @return new dispatcher
     */
    public static SynchronousMessageRouter of(final ObjectMapper objectMapper) {
        return new SynchronousMessageRouter(objectMapper);
    }

    /**
     * Adds action which is executed when provided 'type' and 'version' matches ones from message.
     *
     * @param action action to execute
     * @return this instance
     */
    public <T> SynchronousMessageRouter addEventHandler(final String eventType, final String eventVersion, final Class<T> eventClass, final Consumer<T> action) {
        cases.add(new Case(eventType, eventVersion, (Class<Object>) eventClass, (Consumer<Object>) action));
        return this;
    }

    public <T> SynchronousMessageRouter addEventHandler(final String eventType, final int eventVersion, final Class<T> eventClass, final Consumer<T> action) {
        return addEventHandler(eventType, String.valueOf(eventVersion), eventClass, action);
    }

    /**
     * Adds action which is executed when any of provided cases does not match incoming message
     *
     * @param action action to execute
     * @return this instance
     */
    public SynchronousMessageRouter addDefaultEventHandler(final Consumer<Message<String>> action) {
        defaultCaseAction = action;
        return this;
    }

    /**
     * Enables logging of received messages on INFO level
     *
     * @return this instance
     */
    public SynchronousMessageRouter enableLoggingOnMessageReceived() {
        isLoggingOnMessageReceived = true;
        return this;
    }

    @Override
    public void accept(final Message<String> stringMessage) {
        dispatch(stringMessage);
    }

    @SneakyThrows
    private void dispatch(final Message<String> rawMessage) {

        logIfEnabled(rawMessage);

        final String eventType = rawMessage.getHeaders().get(EVENT_TYPE_HEADER_NAME, String.class);
        final String eventVersion = resolveEventVersion(rawMessage);

        for (final Case eventCase: cases) {

            if(eventCase.eventType.equals(eventType) && eventCase.eventVersion.equals(eventVersion)) {
                final Object event = objectMapper.readValue(rawMessage.getPayload(), eventCase.eventClass);
                eventCase.action.accept(event);
                return;
            }
        }

        if(nonNull(defaultCaseAction)) {
            defaultCaseAction.accept(rawMessage);
        }
    }

    private String resolveEventVersion(final Message<String> rawMessage) {
        final Object version = rawMessage.getHeaders().get(EVENT_VERSION_HEADER_NAME);
        if(isNull(version)) {
            return null;
        }

        return version.toString();
    }

    private void logIfEnabled(final Message<String> message) {
        if(isLoggingOnMessageReceived) {
            log.info("Received message : {}", message.getPayload());
        }
    }

    private record Case(
            String eventType,
            String eventVersion,
            Class<Object> eventClass,
            Consumer<Object> action
    ) { }
}
