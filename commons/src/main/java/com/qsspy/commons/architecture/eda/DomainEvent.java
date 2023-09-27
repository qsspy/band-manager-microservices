package com.qsspy.commons.architecture.eda;

import java.util.UUID;

public interface DomainEvent {

    /**
     * Unique event identifier
     */
    UUID eventId();

    long occurredOn();
}
