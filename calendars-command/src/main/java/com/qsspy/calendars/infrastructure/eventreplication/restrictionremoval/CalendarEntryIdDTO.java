package com.qsspy.calendars.infrastructure.eventreplication.restrictionremoval;

import java.time.LocalDateTime;
import java.util.UUID;

record CalendarEntryIdDTO(
        UUID entryId,
        LocalDateTime eventDate
) {
}
