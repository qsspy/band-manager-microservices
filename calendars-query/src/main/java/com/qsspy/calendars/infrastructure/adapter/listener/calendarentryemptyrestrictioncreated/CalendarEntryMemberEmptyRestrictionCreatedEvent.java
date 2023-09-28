package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryemptyrestrictioncreated;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryMemberEmptyRestrictionCreatedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId,
        UUID entryId,
        EventKind eventKind,
        String memberEmail,
        LocalDateTime eventDate
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "calendar.entry.member.empty.restriction.created";
    static final int EVENT_VERSION = 1;
}