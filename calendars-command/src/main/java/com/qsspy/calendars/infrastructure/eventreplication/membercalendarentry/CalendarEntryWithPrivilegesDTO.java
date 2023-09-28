package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.EventKind;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

record CalendarEntryWithPrivilegesDTO(
        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate,
        EventKind eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description,

        boolean isVisible,
        boolean isVisibleDetails,
        boolean isVisiblePaymentAmount
) {
}
