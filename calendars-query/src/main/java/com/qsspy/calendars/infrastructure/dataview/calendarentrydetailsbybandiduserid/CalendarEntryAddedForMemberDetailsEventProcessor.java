package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember.CalendarEntryAddedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryAddedForMemberDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedForMemberEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryAddedForMemberEvent event) {
        final var entity = CalendarEntryDetailsByBandIdUserId.builder()
                .key(
                        CalendarEntryDetailsByBandIdUserId.Id.builder()
                                .bandId(event.bandId())
                                .userId(event.memberId())
                                .eventId(event.entryId())
                                .build()
                )
                .address(event.address())
                .hoursDuration(event.eventDuration() != null ? event.eventDuration().toHours() : null)
                .description(event.description())
                .isVisible(event.isVisibleDetails())
                .build();

        repository.save(entity);
    }
}
