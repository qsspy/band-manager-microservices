package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberremoved;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberRemovedEventProcessor {

    private final RemoveUserBandMembershipRepository repository;

    void process(final BandMemberRemovedEvent event) {
        repository.removeBandMemberId(
                event.memberId()
        );
    }
}
