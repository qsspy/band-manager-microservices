package com.qsspy.finances.infrastructure.dataview.financeentrybybandiduserid;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.finances.infrastructure.adapter.listener.bandmemberremoved.BandMemberRemovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberRemovedEventProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final FinanceEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteAllByKey_BandIdAndKey_UserId(event.bandId(), event.memberId());
    }
}
