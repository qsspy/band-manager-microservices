package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import java.time.LocalDateTime;
import java.util.UUID;

record EntryIdWithMemberIdDTO(
        UUID entryId,
        LocalDateTime eventDate,
        UUID memberId
) {
}
