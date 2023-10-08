package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged.CalendarEntryMemberPrivilegesChangedEvent;
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
class CalendarEntryMemberPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberPrivilegesChangedEvent> {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryMemberPrivilegesChangedEvent event) {
        repository.updateEntryPrivileges(
                event.isVisible(),
                event.isVisiblePaymentAmount(),
                event.isVisibleDetails(),
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        );

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.DEFAULT_PRIVILEGES_REPLICATED
        ));
    }
}
