package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremovedformember.CalendarEntryRemovedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRemovedForMemberEventProcessor implements DataPropagationEventProcessor<CalendarEntryRemovedForMemberEvent> {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRemovedForMemberEvent event) {
        repository.deleteById(new CalendarEntryByBandIdUserId.Id(
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        ));
    }
}
