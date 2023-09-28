package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember.CalendarEntryAddedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryAddedForMemberEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedForMemberEvent> {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryAddedForMemberEvent event) {
        final var entity = CalendarEntryByBandIdUserId.builder()
                .key(
                        CalendarEntryByBandIdUserId.Id.builder()
                                .bandId(event.bandId())
                                .userId(event.memberId())
                                .eventDate(event.eventDate())
                                .eventId(event.entryId())
                                .build())
                .eventKind(event.eventKind().toString())
                .paymentAmount(event.amount())
                .isVisible(event.isVisible())
                .isVisibleDetails(event.isVisibleDetails())
                .isVisiblePaymentAmount(event.isVisiblePaymentAmount())
                .build();

        repository.save(entity);
    }
}
