package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryAddedForMemberEventProcessor implements DomainEventProcessor<CalendarEntryAddedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryAddedEvent event) {
        final var events = repository.findDefaultCalendarEntryUserPrivileges(event.bandId())
                .stream()
                .map(userPrivileges -> EventMapper.toNotificationEvent(event, userPrivileges))
                .toList();

        publisher.publishAll(events, PublishingMode.ASYNC);
    }
}
