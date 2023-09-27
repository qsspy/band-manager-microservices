package com.qsspy.finances.infrastructure.dataview.financeentrybybandid;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.finances.infrastructure.adapter.listener.financeentryupdated.FinanceEntryUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FinanceEntryUpdatedEventProcessor implements DataPropagationEventProcessor<FinanceEntryUpdatedEvent> {

    private final FinanceEntryByBandIdCassandraRepository repository;

    @Override
    public void process(final FinanceEntryUpdatedEvent event) {
        final var entity = FinanceEntryByBandId.builder()
                .key(
                        FinanceEntryByBandIdKey.builder()
                                .bandId(event.bandId())
                                .creationDate(event.creationDate())
                                .entryId(event.entryId())
                                .build()
                )
                .amount(event.amount())
                .description(event.description())
                .initiatorEmail(event.initiatorEmail())
                .isOutcome(event.isOutcome())
                .build();

        repository.save(entity);
    }
}
