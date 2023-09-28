package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import java.time.LocalDateTime;
import java.util.UUID;

record CalendarEntryIdDTO(
        UUID entryId,
        LocalDateTime eventDate
) {
}
