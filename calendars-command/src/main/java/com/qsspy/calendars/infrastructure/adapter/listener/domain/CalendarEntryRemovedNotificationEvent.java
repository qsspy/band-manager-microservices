package com.qsspy.calendars.infrastructure.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
record CalendarEntryRemovedNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate
) implements NotificationEvent {

    static final String EVENT_TYPE = "calendar.entry.removed";
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
        return "calendar-entry-removed";
    }

    @Override
    public Object partitionKey() {
        return bandId;
    }
}
