package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.infrastructure.adapter.listener.calendarentryaddedformember.CalendarEntryAddedForMemberEvent;
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
class CalendarEntryAddedForMemberDetailsEventProcessor implements DataPropagationEventProcessor<CalendarEntryAddedForMemberEvent> {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;
    private final NotificationEventPublisher publisher;

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

        publisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
    }
}
