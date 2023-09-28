package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionremoved.CalendarEntryMemberRestrictionRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryMemberRestrictionRemovedEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberRestrictionRemovedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryMemberRestrictionRemovedEvent event) {
        repository.deleteById(new CalendarEntryRestrictionByBandIdUserIdKey(
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        ));
    }
}
