package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryremovedformember.CalendarEntryRemovedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRemovedRestrictionEventProcessor implements DataPropagationEventProcessor<CalendarEntryRemovedForMemberEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRemovedForMemberEvent event) {
        repository.deleteById(new CalendarEntryRestrictionByBandIdUserIdKey(
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        ));
    }
}
