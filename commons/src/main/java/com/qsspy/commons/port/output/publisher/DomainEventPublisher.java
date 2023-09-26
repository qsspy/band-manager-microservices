package com.qsspy.commons.port.output.publisher;

import com.qsspy.commons.architecture.eda.DomainEvent;

import java.util.Collection;

public interface DomainEventPublisher {

    /**
     * Publishes event to the output channel
     *
     * @param event event to be published
     */
    void publish(final DomainEvent event);

    /**
     * Publishes events to the output channel
     *
     * @param events events to be published
     */
    default void publishAll(final Collection<DomainEvent> events) {
        events.forEach(this::publish);
    }
}
