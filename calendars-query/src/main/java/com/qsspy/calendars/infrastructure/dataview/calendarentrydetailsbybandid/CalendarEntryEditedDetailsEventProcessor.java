package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded.CalendarEntryAddedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryedited.CalendarEntryEditedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEditedDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryEditedEvent> {

    private final CalendarEntryDetailsByBandIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryEditedEvent event) {
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
    }
}
