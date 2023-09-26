package com.qsspy.bands.domain.band;

import com.qsspy.commons.port.output.publisher.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandCreatedEvent(
        UUID eventId,
        long occurredOn,

        UUID adminId,
        UUID bandId
) implements DomainEvent {

    static final String EVENT_TYPE = "band.created";
    static final int EVENT_VERSION = 1;

    @Override
    public String eventType() {
        return EVENT_TYPE;
    }

    @Override
    public int eventVersion() {
        return EVENT_VERSION;
    }

    @Override
    public String destinationBindingName() {
        return "band-created";
    }

    @Override
    public Object partitionKey() {
        return adminId;
    }
}