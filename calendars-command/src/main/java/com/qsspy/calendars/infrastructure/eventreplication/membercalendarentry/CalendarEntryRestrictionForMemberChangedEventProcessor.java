package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryRestrictionForMemberChangedEvent;
import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryRestrictionForMemberChangedEventProcessor implements DomainEventProcessor<CalendarEntryRestrictionForMemberChangedEvent> {

    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryRestrictionForMemberChangedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
    }
}
