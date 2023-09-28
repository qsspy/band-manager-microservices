package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "calendar.entry.removed";
    static final int EVENT_VERSION = 1;
}
