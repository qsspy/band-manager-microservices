package com.qsspy.finances.domain.finances;

import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DomainEventFactory {

    static FinanceEntryAddedEvent buildBandDefaultPrivilegesChangedEvent(final FinanceEntry entry) {
        return FinanceEntryAddedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .bandId(entry.getBandId().getValue())
                .entryId(entry.getId().getValue())
                .creationDate(entry.getCreationDate().getDate())
                .amount(entry.getAmount().getValue())
                .description(entry.getDescription() != null ? entry.getDescription().getText() : null)
                .initiatorEmail(entry.getInitiator().getEmail())
                .isOutcome(entry.isOutcome)
                .build();
    }

    static private long now() {
        return Instant.now().toEpochMilli();
    }
}
