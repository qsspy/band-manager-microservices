package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryEditedEvent;
import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryEditedForMemberEventProcessor implements DomainEventProcessor<CalendarEntryEditedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryEditedEvent event) {
        final var events = repository.findBandMemberIds(event.bandId())
                .stream()
                .map(memberId -> EventMapper.toNotificationEvent(event, memberId))
                .toList();

        publisher.publishAll(events, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);
    }
}
