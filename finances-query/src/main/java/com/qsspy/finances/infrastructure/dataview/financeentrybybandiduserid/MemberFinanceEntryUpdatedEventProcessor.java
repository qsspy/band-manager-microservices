package com.qsspy.finances.infrastructure.dataview.financeentrybybandiduserid;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.finances.infrastructure.adapter.listener.memberfinanceentryupdated.MemberFinanceEntryUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberFinanceEntryUpdatedEventProcessor implements DataPropagationEventProcessor<MemberFinanceEntryUpdatedEvent> {

    private final FinanceEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final MemberFinanceEntryUpdatedEvent event) {
        final var entity = FinanceEntryByBandIdUserId.builder()
                .key(
                        FinanceEntryByBandIdUserIdKey.builder()
                                .bandId(event.bandId())
                                .userId(event.memberId())
                                .creationDate(event.creationDate())
                                .entryId(event.entryId())
                                .build()
                )
                .isVisible(event.isVisible())
                .amount(event.amount())
                .initiatorEmail(event.initiatorEmail())
                .description(event.description())
                .isOutcome(event.isOutcome())
                .isVisible(event.isVisible())
                .build();

        repository.save(entity);
    }
}