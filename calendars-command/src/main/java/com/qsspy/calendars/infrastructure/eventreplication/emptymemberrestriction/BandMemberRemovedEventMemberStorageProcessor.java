package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.calendars.infrastructure.eventreplication.BandMemberEntity;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberRemovedEventMemberStorageProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final JpaBandMemberEntityRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteById(new BandMemberEntity.Id(event.bandId(), event.memberId()));
    }
}
