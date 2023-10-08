package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
record MemberFinanceEntryUpdatedNotificationEvent(
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

) implements NotificationEvent {
        static final String EVENT_TYPE = "finance.entry.for.member.updated";
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
                return "finance-entry-for-member-updated";
        }

        @Override
        public Object partitionKey() {
                return eventId;
        }
}
