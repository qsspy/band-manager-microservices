package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record FinanceEntryDTO(
        UUID entryId,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        LocalDateTime creationDate,
        boolean isOutcome
) {}
