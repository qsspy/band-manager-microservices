package com.qsspy.gateway.finances.command;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
record AddFinanceEntryCommandRequestBody(
        UUID initiatorsBandId,
        String initiatorsEmail,
        BigDecimal amount,
        @Nullable
        String description,
        boolean isOutcome
) {}
