package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.banddefaultprivilegeschanged.BandDefaultPrivilegesChangedEvent;
import com.qsspy.calendars.infrastructure.eventreplication.DefaultBandPrivileges;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandDefaultPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<BandDefaultPrivilegesChangedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;

    @Override
    public void process(final BandDefaultPrivilegesChangedEvent event) {
        final var entity = DefaultBandPrivileges.builder()
                .bandId(event.bandId())
                .canSeeCalendarEntryByDefault(event.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(event.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(event.canSeeCalendarEntryDetailsByDefault())
                .build();

        repository.save(entity);
    }
}
