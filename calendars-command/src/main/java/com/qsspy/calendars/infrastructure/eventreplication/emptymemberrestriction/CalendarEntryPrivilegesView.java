package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.domain.calendar.EventKind;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalendarEntryPrivilegesView(
        UUID id,
        EventKind eventKind,
        LocalDateTime eventDate
) {
}
