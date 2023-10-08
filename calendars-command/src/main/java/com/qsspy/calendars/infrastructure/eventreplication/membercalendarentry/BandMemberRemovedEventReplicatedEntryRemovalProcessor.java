package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberRemovedEventReplicatedEntryRemovalProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        final var notificationEvents = repository.findAllCalendarEntryIdsByBandId(event.bandId())
                .stream()
                .map(dto -> EventMapper.toNotificationEvent(event, dto))
                .toList();

        publisher.publishAll(notificationEvents, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);
    }
}
