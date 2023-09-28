package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryMemberRestrictionRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId,
        UUID entryId,
        LocalDateTime eventDate
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "calendar.entry.member.restriction.removed";
    static final int EVENT_VERSION = 1;
}
