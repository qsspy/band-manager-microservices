package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryemptyrestrictioncreated.CalendarEntryMemberEmptyRestrictionCreatedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEmptyRestrictionCreatedEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberEmptyRestrictionCreatedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryMemberEmptyRestrictionCreatedEvent event) {
        final var entity = CalendarEntryRestrictionByBandIdUserId.builder()
                .key(
                        CalendarEntryRestrictionByBandIdUserIdKey.builder()
                                .bandId(event.bandId())
                                .userId(event.memberId())
                                .eventDate(event.eventDate())
                                .eventId(event.entryId())
                                .build()
                )
                .eventKind(event.eventKind().toString())
                .memberEmail(event.memberEmail())
                .isVisible(null)
                .isVisibleDetails(null)
                .isVisiblePaymentAmount(null)
                .build();

        repository.save(entity);
    }
}
