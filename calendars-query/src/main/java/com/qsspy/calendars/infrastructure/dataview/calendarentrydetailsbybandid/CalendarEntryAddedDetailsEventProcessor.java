package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded.CalendarEntryAddedEvent;
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
class CalendarEntryAddedDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedEvent> {

    private final CalendarEntryDetailsByBandIdCassandraRepository repository;
    private final NotificationEventPublisher notificationEventPublisher;

    @Override
    public void process(final CalendarEntryAddedEvent event) {
        final var entity = CalendarEntryDetailsByBandId.builder()
                .key(
                        CalendarEntryDetailsByBandId.Id.builder()
                                .bandId(event.bandId())
                                .eventId(event.entryId())
                                .build())
                .address(event.address())
                .hoursDuration(event.eventDuration() != null ? event.eventDuration().toHours() : null)
                .description(event.description())
                .build();

        repository.save(entity);

        notificationEventPublisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
    }
}
