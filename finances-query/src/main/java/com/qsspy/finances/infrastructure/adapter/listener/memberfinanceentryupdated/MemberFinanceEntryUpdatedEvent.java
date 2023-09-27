package com.qsspy.finances.infrastructure.adapter.listener.memberfinanceentryupdated;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record MemberFinanceEntryUpdatedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID entryId,
        UUID memberId,
        BigDecimal amount,
        LocalDateTime creationDate,
        @Nullable
        String description,
        String initiatorEmail,
        boolean isOutcome,
        boolean isVisible

) implements DataPropagationEvent {
        static final String EVENT_TYPE = "finance.entry.for.member.updated";
        static final int EVENT_VERSION = 1;
}
