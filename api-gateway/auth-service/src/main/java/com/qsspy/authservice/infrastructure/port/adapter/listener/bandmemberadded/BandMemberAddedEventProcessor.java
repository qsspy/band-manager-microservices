package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberadded;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberAddedEventProcessor {

    private final UpdateUserBandMemberRepository updateRepository;

    void process(final BandMemberAddedEvent event) {
        updateRepository.updateBandMemberId(
                event.memberId(),
                event.bandId()
        );
    }
}
