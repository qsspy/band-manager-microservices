package com.qsspy.bands.infrastructure.adapter.listener.domain;

import com.qsspy.bands.domain.band.event.*;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class DomainEventListener {

    private final NotificationEventPublisher publisher;

    @EventListener
    public void handle(final BandCreatedEvent event) {
        final var notificationEvent = EventMapper.fromDomainEvent(event);
        publisher.publish(notificationEvent);
    }

    @EventListener
    public void handle(final BandDefaultPrivilegesChangedEvent event) {
        final var notificationEvent = EventMapper.fromDomainEvent(event);
        publisher.publish(notificationEvent);
    }

    @EventListener
    public void handle(final BandMemberAddedEvent event) {
        final var notificationEvent = EventMapper.fromDomainEvent(event);
        publisher.publish(notificationEvent);
    }

    @EventListener
    public void handle(final BandMemberPrivilegesChangedEvent event) {
        final var notificationEvent = EventMapper.fromDomainEvent(event);
        publisher.publish(notificationEvent);
    }

    @EventListener
    public void handle(final BandMemberRemovedEvent event) {
        final var notificationEvent = EventMapper.fromDomainEvent(event);
        publisher.publish(notificationEvent);
    }
}
