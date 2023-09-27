package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CalendarEntryAddedEventProcessor implements DomainEventProcessor<CalendarEntryAddedEvent> {

    private final JpaBandMemberEntityRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final CalendarEntryAddedEvent event) {
        final var events = repository
                .findAllById_BandId(event.bandId())
                .stream()
                .map(entity -> EventMapper.toNotificationEvent(entity, event))
                .toList();

        publisher.publishAll(events, PublishingMode.ASYNC);
    }
}
