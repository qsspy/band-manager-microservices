package com.qsspy.calendars.infrastructure.adapter.listener.domain;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryEditedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRemovedEvent;
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
    private final List<DomainEventProcessor<CalendarEntryEditedEvent>> calendarEntryEditedProcessors;
    private final List<DomainEventProcessor<CalendarEntryRemovedEvent>> calendarEntryRemovedProcessors;
    private final List<DomainEventProcessor<CalendarEntryRestrictionForMemberChangedEvent>> calendarEntryRestrictionChangedProcessors;

    @EventListener
    public void handle(final CalendarEntryRestrictionForMemberChangedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
        DomainEventProcessor.processByAll(event, calendarEntryRestrictionChangedProcessors);
    }

    @EventListener
    public void handle(final CalendarEntryAddedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
        DomainEventProcessor.processByAll(event, calendarEntryAddedProcessors);
    }

    @EventListener
    public void handle(final CalendarEntryEditedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
        DomainEventProcessor.processByAll(event, calendarEntryEditedProcessors);
    }

    @EventListener
    public void handle(final CalendarEntryRemovedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
        DomainEventProcessor.processByAll(event, calendarEntryRemovedProcessors);
    }
}
