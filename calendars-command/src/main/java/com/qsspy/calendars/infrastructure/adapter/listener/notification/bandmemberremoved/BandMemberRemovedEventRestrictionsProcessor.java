package com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberRemovedEventRestrictionsProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final JpaRestrictionRemovalRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteAllById_MemberId(event.memberId());
    }
}
