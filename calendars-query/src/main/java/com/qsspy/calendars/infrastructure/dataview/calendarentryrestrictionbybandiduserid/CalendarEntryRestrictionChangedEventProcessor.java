package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionchanged.CalendarEntryRestrictionChangedEvent;
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
class CalendarEntryRestrictionChangedEventProcessor implements DataPropagationEventProcessor<CalendarEntryRestrictionChangedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryRestrictionChangedEvent event) {
        repository.updateRestrictionPrivileges(
                event.bandId(),
                event.memberId(),
                event.entryId(),
                event.eventDate(),
                event.isVisible(),
                event.isVisibleDetails(),
                event.isVisiblePaymentAmount()
        );

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
    }
}
