package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded.CalendarEntryAddedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryAddedEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedEvent> {

    private final CalendarEntryByBandIdCassandraRepository repository;

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
    }
}
