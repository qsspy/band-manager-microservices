package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremoved.CalendarEntryRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRemovedDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryRemovedEvent> {

    private final CalendarEntryDetailsByBandIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRemovedEvent event) {
        repository.deleteById(new CalendarEntryDetailsByBandId.Id(event.bandId(), event.entryId()));
    }
}
