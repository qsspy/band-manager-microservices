package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember.CalendarEntryAddedForMemberEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremovedformember.CalendarEntryRemovedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRemovedForMemberDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryRemovedForMemberEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRemovedForMemberEvent event) {

        repository.deleteById(new CalendarEntryDetailsByBandIdUserId.Id(
                event.bandId(),
                event.memberId(),
                event.entryId()
        ));
    }
}
