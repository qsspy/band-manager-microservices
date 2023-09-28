package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded.CalendarEntryAddedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremoved.CalendarEntryRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRemovedEventProcessor implements DataPropagationEventProcessor<CalendarEntryRemovedEvent> {

    private final CalendarEntryByBandIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRemovedEvent event) {
        repository.deleteById(new CalendarEntryByBandId.Id(event.bandId(), event.eventDate(), event.entryId()));
    }
}
