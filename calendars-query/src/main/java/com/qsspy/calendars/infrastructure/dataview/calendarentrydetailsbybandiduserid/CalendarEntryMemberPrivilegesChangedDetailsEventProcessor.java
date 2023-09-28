package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged.CalendarEntryMemberPrivilegesChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryMemberPrivilegesChangedDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberPrivilegesChangedEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryMemberPrivilegesChangedEvent event) {
        repository.updateEntryDetailsViewPrivilege(
                event.isVisibleDetails(),
                event.bandId(),
                event.memberId(),
                event.entryId()
        );
    }
}
