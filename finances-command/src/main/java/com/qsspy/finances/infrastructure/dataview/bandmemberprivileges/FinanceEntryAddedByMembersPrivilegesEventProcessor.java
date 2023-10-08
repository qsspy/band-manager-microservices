package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.DomainEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FinanceEntryAddedByMembersPrivilegesEventProcessor implements DomainEventProcessor<FinanceEntryAddedEvent> {

    private final JpaBandMemberPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final FinanceEntryAddedEvent event) {

        final var notificationEvents = repository.findAllById_BandId(event.bandId())
                .stream()
                .map(privileges -> EventMapper.toMemberFinanceEntryUpdatedNotificationEvent(event, privileges.getId().getMemberId(), calculateEntryVisibility(privileges, event)))
                .toList();

        publisher.publishAll(notificationEvents, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);
    }

    private boolean calculateEntryVisibility(final BandMemberPrivileges privileges, final FinanceEntryAddedEvent event) {
        return (event.isOutcome() && privileges.isCanSeeFinanceOutcomeEntries()) || (!event.isOutcome() && privileges.isCanSeeFinanceIncomeEntries());
    }
}
