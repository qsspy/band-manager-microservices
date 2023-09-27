package com.qsspy.finances.infrastructure.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
record FinanceEntryUpdatedNotificationEvent(
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

) implements NotificationEvent {
        static final String EVENT_TYPE = "finance.entry.updated";
        static final int EVENT_VERSION = 1;

        @Override
        public String eventType() {
                return EVENT_TYPE;
        }

        @Override
        public int eventVersion() {
                return EVENT_VERSION;
        }

        @Override
        public String destinationBindingName() {
                return "finance-entry-updated";
        }

        @Override
        public Object partitionKey() {
                return bandId;
        }
}
