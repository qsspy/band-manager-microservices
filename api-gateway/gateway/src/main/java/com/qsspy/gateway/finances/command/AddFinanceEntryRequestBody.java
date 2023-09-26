package com.qsspy.gateway.finances.command;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;

record AddFinanceEntryRequestBody(
        BigDecimal amount,
        @Nullable
        String description,
        boolean isOutcome
) {}
