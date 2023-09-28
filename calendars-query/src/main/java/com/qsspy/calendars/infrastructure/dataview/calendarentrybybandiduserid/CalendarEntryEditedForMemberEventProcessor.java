package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryeditedformember.CalendarEntryEditedForMemberEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEditedForMemberEventProcessor implements DataPropagationEventProcessor<CalendarEntryEditedForMemberEvent> {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final CalendarEntryEditedForMemberEvent event) {

        if(!event.oldEventDate().isEqual(event.eventDate())) {

            final var oldId = new CalendarEntryByBandIdUserId.Id(
                    event.bandId(),
                    event.memberId(),
                    event.oldEventDate(),
                    event.eventId()
            );

            final var newId = new CalendarEntryByBandIdUserId.Id(
                    event.bandId(),
                    event.memberId(),
                    event.eventDate(),
                    event.eventId()
            );

            repository
                    .findById(oldId)
                    .ifPresent(oldEntity -> {

                        final var newEntity = CalendarEntryByBandIdUserId.builder()
                                .key(newId)
                                .eventKind(event.eventKind().toString())
                                .paymentAmount(event.amount())
                                .isVisible(oldEntity.isVisible())
                                .isVisibleDetails(oldEntity.isVisibleDetails())
                                .isVisiblePaymentAmount(oldEntity.isVisiblePaymentAmount())
                                .build();

                        repository.deleteById(oldId);
                        repository.save(newEntity);

                    });

            return;
        }

        repository.updateEntryData(
                event.eventKind().toString(),
                event.amount(),
                event.bandId(),
                event.memberId(),
                event.eventDate(),
                event.entryId()
        );
    }
}
