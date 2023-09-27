package com.qsspy.finances.infrastructure.adapter.listener.domain;

import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {

    static FinanceEntryUpdatedNotificationEvent toNotificationEvent(final FinanceEntryAddedEvent event) {
        return FinanceEntryUpdatedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .bandId(event.bandId())
                .entryId(event.entryId())
                .amount(event.amount())
                .creationDate(event.creationDate())
                .description(event.description())
                .initiatorEmail(event.initiatorEmail())
                .isOutcome(event.isOutcome())
                .build();
    }
}
