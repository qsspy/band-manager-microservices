package com.qsspy.commons.port.output.publisher.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qsspy.commons.architecture.eda.NotificationEvent;

import java.util.UUID;

public record MeasurementNotificationEvent(
        UUID eventId,
        long occurredOn,

        @JsonIgnore
        MeasurementType measurementType
) implements NotificationEvent {

    private static final int EVENT_VERSION = 1;

    @Override
    public String eventType() {
        return measurementType.toString();
    }

    @Override
    public int eventVersion() {
        return EVENT_VERSION;
    }

    @Override
    public String destinationBindingName() {
        return "measurements";
    }

    @Override
    public Object partitionKey() {
        return eventId;
    }
}
