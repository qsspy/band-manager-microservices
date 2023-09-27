package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberRemovedEventProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteAllByKey_BandIdAndKey_UserId(event.bandId(), event.memberId());
    }
}
