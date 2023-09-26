package com.qsspy.finances.infrastructure.adapter.controller;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

record AddFinanceEntryRequestBody(
        UUID initiatorsBandId,
        String initiatorsEmail,
        BigDecimal amount,
        @Nullable
        String description,
        boolean isOutcome
) {}
