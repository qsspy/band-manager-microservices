package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberAddedEventRestrictionReplicationProcessor implements DataPropagationEventProcessor<BandMemberAddedEvent> {

    private final CalendarEntryPrivilegesViewRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandMemberAddedEvent event) {
        final var events = repository.getViewsByBandId(event.bandId())
                .stream()
                .map(entry -> EventMapper.toNotificationEvent(entry, event))
                .toList();

        publisher.publishAll(events, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);
    }
}
