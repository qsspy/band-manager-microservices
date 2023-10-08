package com.qsspy.commons.port.output.publisher.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qsspy.commons.architecture.eda.NotificationEvent;

import java.util.UUID;

public record MeasurementNotificationEvent(
        UUID eventId,
        long occurredOn,

        MeasurementType measurementType,
        int recordNumber,
        int description
) implements NotificationEvent {

    private static final int EVENT_VERSION = 1;
    private static final String EVENT_TYPE = "measurement";

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
        return "measurements";
    }

    @Override
    public Object partitionKey() {
        return eventId;
    }
}
