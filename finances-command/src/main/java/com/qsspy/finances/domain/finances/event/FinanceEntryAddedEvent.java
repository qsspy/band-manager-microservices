package com.qsspy.finances.domain.finances.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record FinanceEntryAddedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID entryId,
        LocalDateTime creationDate,
        BigDecimal amount,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome

) implements DomainEvent { }
