package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberprivilegeschanged.BandMemberPrivilegesChangedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
class BandMemberPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<BandMemberPrivilegesChangedEvent> {

    private final JpaBandMemberPrivilegesRepository repository;
    private final VisibilityFinanceEntryRepository financeEntriesRepository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandMemberPrivilegesChangedEvent event) {
        repository.findById(new BandMemberPrivilegesId(event.bandId(), event.memberId()))
                .ifPresentOrElse(
                        privileges -> handleUpdateOfBandMemberPrivileges(event, privileges),
                        () -> handleNewBandMemberPrivileges(event)
                );
    }

    private void handleUpdateOfBandMemberPrivileges(final BandMemberPrivilegesChangedEvent event, final BandMemberPrivileges privileges) {
        if(privileges.isCanSeeFinanceIncomeEntries() == event.canSeeFinanceIncomeEntries() && privileges.isCanSeeFinanceOutcomeEntries() == event.canSeeFinanceOutcomeEntries()) {
            log.info("Financial privileges were not changed");
            return;
        }

        final List<NotificationEvent> events = new LinkedList<>();
        if(privileges.isCanSeeFinanceIncomeEntries() != event.canSeeFinanceIncomeEntries()) {

            events.addAll(
                    financeEntriesRepository.findEntriesByBandIdAndIsIncome(event.bandId()).stream()
                            .map(entry -> EventMapper.toMemberFinanceEntryUpdatedNotificationEvent(
                                    entry,
                                    event.bandId(),
                                    event.memberId(),
                                    event.canSeeFinanceIncomeEntries()
                            ))
                            .toList()
            );

            privileges.setCanSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries());
        }

        if(privileges.isCanSeeFinanceOutcomeEntries() != event.canSeeFinanceOutcomeEntries()) {

            events.addAll(
                    financeEntriesRepository.findEntriesByBandIdAndIsOutcome(event.bandId()).stream()
                            .map(entry -> EventMapper.toMemberFinanceEntryUpdatedNotificationEvent(
                                    entry,
                                    event.bandId(),
                                    event.memberId(),
                                    event.canSeeFinanceOutcomeEntries()
                            ))
                            .toList()
            );


            privileges.setCanSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries());
        }

        repository.save(privileges);
        publisher.publishAll(events, PublishingMode.ASYNC);
    }

    private void handleNewBandMemberPrivileges(final BandMemberPrivilegesChangedEvent event) {
        final List<NotificationEvent> events = financeEntriesRepository.findByBandId(event.bandId())
                .stream()
                .map(entry -> EventMapper.toMemberFinanceEntryUpdatedNotificationEvent(
                        entry,
                        event.bandId(),
                        event.memberId(),
                        calculateEntryVisibility(event, entry)
                ))
                .toList();

        final var entity = EntityBuilder.build(event);
        repository.save(entity);
        publisher.publishAll(events);
    }

    private boolean calculateEntryVisibility(final BandMemberPrivilegesChangedEvent event, final FinanceEntryDTO entryDTO) {
        return (entryDTO.isOutcome() && event.canSeeFinanceOutcomeEntries()) || (!entryDTO.isOutcome() && event.canSeeFinanceIncomeEntries());
    }
}
