package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.EventKind;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
record CalendarEntryEditedForMemberNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        UUID memberId,
        LocalDateTime oldEventDate,
        LocalDateTime eventDate,
        EventKind eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
) implements NotificationEvent {

    static final String EVENT_TYPE = "calendar.entry.for.member.edited";
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
        return "calendar-entry-for-member-edited";
    }

    @Override
    public Object partitionKey() {
        return bandId;
    }
}
