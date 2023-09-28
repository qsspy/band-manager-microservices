package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryeditedformember.CalendarEntryEditedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEditedDetailsForMemberEventProcessor implements DataPropagationEventProcessor<CalendarEntryEditedForMemberEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryEditedForMemberEvent event) {

        repository.updateEntryDetailsData(
                event.address(),
                event.eventDuration() != null ? event.eventDuration().toHours() : null,
                event.description(),
                event.bandId(),
                event.memberId(),
                event.entryId()
        );
    }
}
