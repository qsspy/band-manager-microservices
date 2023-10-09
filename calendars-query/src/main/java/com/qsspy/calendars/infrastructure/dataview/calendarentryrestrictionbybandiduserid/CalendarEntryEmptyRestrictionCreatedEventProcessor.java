package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryemptyrestrictioncreated.CalendarEntryMemberEmptyRestrictionCreatedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CalendarEntryEmptyRestrictionCreatedEventProcessor implements DataPropagationEventProcessor<CalendarEntryMemberEmptyRestrictionCreatedEvent> {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

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

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
    }
}
