package com.qsspy.bands.infrastructure.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandCreatedNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID adminId,
        UUID bandId
) implements NotificationEvent {

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
        return eventId;
    }
}