package com.qsspy.calendars.application.entries.list.port.output.dto;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryDTO(
        UUID id,
        EventKind eventKind,
        LocalDateTime eventDate,
        BigDecimal amount,
        @Nullable
        Boolean canSeeDetails,
        boolean canSeeDetailsByDefault,
        @Nullable
        Boolean canSeePayment,
        boolean canSeePaymentByDefault
) { }
