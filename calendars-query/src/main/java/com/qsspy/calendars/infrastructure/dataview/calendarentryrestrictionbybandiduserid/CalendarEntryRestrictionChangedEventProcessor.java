package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberrestrictionchanged.CalendarEntryRestrictionChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRestrictionChangedEventProcessor implements DataPropagationEventProcessor<CalendarEntryRestrictionChangedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryRestrictionChangedEvent event) {
        repository.updateRestrictionPrivileges(
                event.bandId(),
                event.memberId(),
                event.entryId(),
                event.eventDate(),
                event.isVisible(),
                event.isVisibleDetails(),
                event.isVisiblePaymentAmount()
        );
    }
}
