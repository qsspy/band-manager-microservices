package com.qsspy.gateway.finances.query;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
record GetFinanceEntriesQueryResponse(
        List<FinanceEntry> entries,
        BigDecimal totalBudget
) implements QueryResult {

    @Builder
    public record FinanceEntry(
            UUID entryId,
            BigDecimal amount,
            @Nullable
            String description,
            String initiatorEmail,
            boolean isOutcome
    ) {}
}
