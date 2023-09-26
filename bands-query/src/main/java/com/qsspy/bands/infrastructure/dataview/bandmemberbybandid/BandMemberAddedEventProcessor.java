package com.qsspy.bands.infrastructure.dataview.bandmemberbybandid;

import com.qsspy.bands.infrastructure.adapter.listener.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberAddedEventProcessor implements DataPropagationEventProcessor<BandMemberAddedEvent> {

    private final BandMemberByBandIdCassandraRepository repository;

    @Override
    public void process(final BandMemberAddedEvent event) {

        final var entity = BandMemberByBandId.builder()
                .key(new BandMemberByBandIdKey(event.bandId(), event.memberEmail()))
                .userId(event.memberId())
                .firstName(event.memberFirstName())
                .lastName(event.memberLastName())
                .build();

        repository.save(entity);
    }
}
