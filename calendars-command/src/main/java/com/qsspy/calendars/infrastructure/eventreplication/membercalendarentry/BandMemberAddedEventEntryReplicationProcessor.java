package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberAddedEventEntryReplicationProcessor implements DataPropagationEventProcessor<BandMemberAddedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandMemberAddedEvent event) {
        final var notificationEvents = repository.findAllCalendarEntriesWithDefaultPrivileges(event.bandId())
                .stream()
                .map(dto -> EventMapper.toNotificationEvent(dto, event.memberId()))
                .toList();

        publisher.publishAll(notificationEvents, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);
    }
}
