package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded.CalendarEntryAddedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryedited.CalendarEntryEditedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEditedEventProcessor implements DataPropagationEventProcessor<CalendarEntryEditedEvent> {

    private final CalendarEntryByBandIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryEditedEvent event) {
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

        if(!event.oldEventDate().isEqual(event.eventDate())) {
            repository.deleteById(new CalendarEntryByBandId.Id(event.bandId(), event.oldEventDate(), event.entryId()));
        }
        repository.save(entity);
    }
}
