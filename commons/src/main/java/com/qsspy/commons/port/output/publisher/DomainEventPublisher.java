package com.qsspy.commons.port.output.publisher;

public interface DomainEventPublisher {

    /**
     * Publishes event to the output channel
     *
     * @param event event to be published
     */
    void publish(final DomainEvent event);
}
