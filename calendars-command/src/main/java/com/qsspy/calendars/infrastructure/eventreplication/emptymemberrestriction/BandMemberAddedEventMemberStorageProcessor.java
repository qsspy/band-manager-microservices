package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.calendars.infrastructure.eventreplication.BandMemberEntity;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberAddedEventMemberStorageProcessor implements DataPropagationEventProcessor<BandMemberAddedEvent> {

    private final JpaBandMemberEntityRepository repository;

    @Override
    public void process(final BandMemberAddedEvent event) {
        final var entity = new BandMemberEntity(
                new BandMemberEntity.Id(
                        event.bandId(),
                        event.memberId()
                ),
                event.memberEmail()
        );

        repository.save(entity);
    }
}
