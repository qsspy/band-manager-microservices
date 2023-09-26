package com.qsspy.commons.port.output.publisher;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class MessageBrokerDomainEventPublisher implements DomainEventPublisher {

    private static final String EVENT_TYPE_HEADER_NAME = "type";
    private static final String EVENT_VERSION_HEADER_NAME = "version";
    private static final String PARTITION_KEY_HEADER_NAME = "partitionKey";

    private final StreamBridge streamBridge;

    @Override
    public void publish(final DomainEvent event) {

        final var message = MessageBuilder
                .withPayload(event)
                .setHeader(EVENT_TYPE_HEADER_NAME, event.eventType())
                .setHeader(EVENT_VERSION_HEADER_NAME, event.eventVersion())
                .setHeader(PARTITION_KEY_HEADER_NAME, event.partitionKey())
                .build();

        log.info("Publishing event: {}", message);

        streamBridge.send(event.destinationBindingName(), message);
    }
}
