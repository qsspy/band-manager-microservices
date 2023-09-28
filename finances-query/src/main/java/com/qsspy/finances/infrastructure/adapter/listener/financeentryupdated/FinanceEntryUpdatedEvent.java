package com.qsspy.finances.infrastructure.adapter.listener.financeentryupdated;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record FinanceEntryUpdatedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID entryId,
        BigDecimal amount,
        LocalDateTime creationDate,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome

) implements DataPropagationEvent {
        static final String EVENT_TYPE = "finance.entry.updated";
        static final int EVENT_VERSION = 1;
}
