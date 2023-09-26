package com.qsspy.authservice.infrastructure.port.adapter.listener.bandcreated;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandCreatedEventProcessor {

    private final UpdateUserBandAdminRepository updateRepository;

    void process(final BandCreatedEvent event) {
        updateRepository.updateBandAdminId(
                event.adminId(),
                event.bandId()
        );
    }
}
