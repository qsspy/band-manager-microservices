package com.qsspy.finances.domain.finances;

import com.qsspy.finances.domain.finances.dto.FinanceEntrySpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FinanceEntryFactory {

    public static FinanceEntry createNewFinanceEntry(final FinanceEntrySpecification spec) {
        final var entry = FinanceEntry.builder()
                .id(new AggregateId(UUID.randomUUID()))
                .bandId(new BandId(spec.bandId()))
                .description(spec.description() != null ? new Description(spec.description()) : null)
                .amount(new Amount(spec.amount()))
                .initiator(new Initiator(spec.initiatorEmail()))
                .creationDate(new CreationDate(LocalDateTime.now()))
                .isOutcome(spec.isOutcome())
                .build()
                .generateInitialEvents();

        entry.validateCurrentState();

        return entry;
    }
}
