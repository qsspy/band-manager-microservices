package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionchanged;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryRestrictionChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate,

        boolean isVisible,
        boolean isVisibleDetails,
        boolean isVisiblePaymentAmount
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "calendar.entry.member.restriction.changed";
    static final int EVENT_VERSION = 1;
}