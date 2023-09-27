package com.qsspy.finances.application.entries.port.output.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FinanceEntryDTO(
        UUID entryId,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome
) {}
