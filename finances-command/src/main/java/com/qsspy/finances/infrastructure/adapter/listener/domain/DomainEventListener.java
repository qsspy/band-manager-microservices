package com.qsspy.finances.infrastructure.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class DomainEventListener {

    private final NotificationEventPublisher publisher;
    private final List<DomainEventProcessor<FinanceEntryAddedEvent>> financeEntryAddedEventProcessors;

    @EventListener
    public void handle(final FinanceEntryAddedEvent event) {
        final var notificationEvent = EventMapper.toNotificationEvent(event);
        publisher.publish(notificationEvent);
        DomainEventProcessor.processByAll(event, financeEntryAddedEventProcessors);
    }
}
