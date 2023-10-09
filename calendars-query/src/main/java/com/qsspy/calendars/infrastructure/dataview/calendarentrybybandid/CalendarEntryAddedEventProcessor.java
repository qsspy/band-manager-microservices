package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

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
class CalendarEntryAddedEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedEvent> {

    private final CalendarEntryByBandIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryAddedEvent event) {
        final var entity = CalendarEntryByBandId.builder()
                .key(
                        CalendarEntryByBandId.Id.builder()
                                .bandId(event.bandId())
                                .eventDate(event.eventDate())
                                .eventId(event.entryId())
                                .build())
                .eventKind(event.eventKind().toString())
                .paymentAmount(event.amount())
                .build();

        repository.save(entity);

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
    }
}
