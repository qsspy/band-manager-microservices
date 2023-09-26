package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import com.qsspy.bands.infrastructure.adapter.listener.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberRemovedEventProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final BandMemberByBandIdCassandraRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteById(new BandMemberByBandIdKey(
                event.bandId(), event.memberEmail()
        ));
    }
}
