package com.qsspy.commons.port.output.publisher.notification;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageBrokerNotificationEventPublisher implements NotificationEventPublisher {

    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";
    private static final String PARTITION_KEY_HEADER_NAME = "partitionKey";

    private final StreamBridge streamBridge;

    private final ExecutorService asyncPublishingExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public void publish(final NotificationEvent event) {

        final var message = MessageBuilder
                .withPayload(event)
                .setHeader(EVENT_TYPE_HEADER_NAME, event.eventType())
                .setHeader(EVENT_VERSION_HEADER_NAME, event.eventVersion())
                .setHeader(PARTITION_KEY_HEADER_NAME, event.partitionKey())
                .build();

        log.info("Publishing event: {}", message);

        streamBridge.send(event.destinationBindingName(), message);
    }

    @Override
    public void publishAll(final Collection<NotificationEvent> events, final PublishingMode publishingMode) {
        if(PublishingMode.SYNC == publishingMode) {
            publishSynchronously(events);
        }
        publishAsynchronously(events);
    }

    private void publishAsynchronously(final Collection<NotificationEvent> events) {
        events.forEach(event -> asyncPublishingExecutor.submit(() -> publish(event)));
    }

    private void publishSynchronously(final Collection<NotificationEvent> events) {
        events.forEach(this::publish);
    }
}
