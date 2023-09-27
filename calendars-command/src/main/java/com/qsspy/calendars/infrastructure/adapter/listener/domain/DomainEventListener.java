package com.qsspy.calendars.infrastructure.adapter.listener.domain;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRestrictionForMemberChangedEvent;
import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class DomainEventListener {

    private final NotificationEventPublisher publisher;
    private final List<DomainEventProcessor<CalendarEntryAddedEvent>> calendarEntryAddedProcessors;

    @EventListener
    public void handle(final CalendarEntryRestrictionForMemberChangedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
    }

    @EventListener
    public void handle(final CalendarEntryAddedEvent event) {
        DomainEventProcessor.processByAll(event, calendarEntryAddedProcessors);
    }
}
