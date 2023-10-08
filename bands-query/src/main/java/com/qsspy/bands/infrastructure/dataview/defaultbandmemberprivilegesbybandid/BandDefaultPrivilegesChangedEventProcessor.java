package com.qsspy.bands.infrastructure.dataview.defaultbandmemberprivilegesbybandid;

import com.qsspy.bands.infrastructure.adapter.listener.banddefaultprivilegeschanged.BandDefaultPrivilegesChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class BandDefaultPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<BandDefaultPrivilegesChangedEvent> {

    private final DefaultBandMemberPrivilegesByBandIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandDefaultPrivilegesChangedEvent event) {
        final var entity = DefaultBandMemberPrivilegesByBandId.builder()
                .bandId(event.bandId())
                .canAccessCalendar(event.canAccessCalendar())
                .canAddCalendarEntries(event.canAddCalendarEntries())
                .canEditCalendarEntries(event.canEditCalendarEntries())
                .canDeleteCalendarEntries(event.canDeleteCalendarEntries())
                .canAccessFinanceHistory(event.canAccessFinanceHistory())
                .canAddFinanceEntries(event.canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries())
                .canSeeCalendarEntryByDefault(event.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(event.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(event.canSeeCalendarEntryDetailsByDefault())
                .build();

        repository.save(entity);

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.DEFAULT_PRIVILEGES_REPLICATED
        ));
    }
}
