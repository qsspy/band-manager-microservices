package com.qsspy.calendars.infrastructure.eventreplication.restrictionremoval;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberRemovedEventRestrictionsProcessor implements DataPropagationEventProcessor<BandMemberRemovedEvent> {

    private final JpaRestrictionRemovalRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandMemberRemovedEvent event) {
        repository.deleteAllById_MemberId(event.memberId());

        final var notificationEvents = repository.findAllCalendarEntryIdsByBandId(event.bandId())
                .stream()
                .map(dto -> EventMapper.toNotificationEvent(event, dto))
                .toList();

        publisher.publishAll(notificationEvents, PublishingMode.ASYNC);
    }
}
