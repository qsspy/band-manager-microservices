package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {

    static NotificationEvent toMemberFinanceEntryUpdatedNotificationEvent(final FinanceEntryAddedEvent event, final UUID memberId, final boolean isVisible) {
        return MemberFinanceEntryUpdatedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .bandId(event.bandId())
                .entryId(event.entryId())
                .memberId(memberId)
                .amount(event.amount())
                .creationDate(event.creationDate())
                .description(event.description())
                .initiatorEmail(event.initiatorEmail())
                .isOutcome(event.isOutcome())
                .isVisible(isVisible)
                .build();
    }

    static NotificationEvent toMemberFinanceEntryUpdatedNotificationEvent(final FinanceEntryDTO dto, final UUID bandId, final UUID memberId, final boolean isVisible) {
        return MemberFinanceEntryUpdatedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .bandId(bandId)
                .entryId(dto.entryId())
                .memberId(memberId)
                .amount(dto.amount())
                .creationDate(dto.creationDate())
                .description(dto.description())
                .initiatorEmail(dto.initiatorEmail())
                .isOutcome(dto.isOutcome())
                .isVisible(isVisible)
                .build();
    }
}
