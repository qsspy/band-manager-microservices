package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberRemovedEventProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final JpaBandMemberPrivilegesRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteById_BandIdAndId_MemberId(event.bandId(), event.memberId());
    }
}
