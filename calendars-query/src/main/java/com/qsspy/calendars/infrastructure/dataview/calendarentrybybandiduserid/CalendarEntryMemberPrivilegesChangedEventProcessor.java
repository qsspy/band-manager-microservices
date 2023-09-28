package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged.CalendarEntryMemberPrivilegesChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryMemberPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberPrivilegesChangedEvent> {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryMemberPrivilegesChangedEvent event) {
        repository.updateEntryPrivileges(
                event.isVisible(),
                event.isVisiblePaymentAmount(),
                event.isVisibleDetails(),
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        );
    }
}
