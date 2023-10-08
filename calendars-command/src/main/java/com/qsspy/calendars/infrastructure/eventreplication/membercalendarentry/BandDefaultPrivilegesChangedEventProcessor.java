package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.banddefaultprivilegeschanged.BandDefaultPrivilegesChangedEvent;
import com.qsspy.calendars.infrastructure.eventreplication.DefaultBandPrivileges;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.PublishingMode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
class BandDefaultPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<BandDefaultPrivilegesChangedEvent> {

    private final JpaDefaultBandPrivilegesRepository repository;
    private final NotificationEventPublisher publisher;

    @Override
    public void process(final BandDefaultPrivilegesChangedEvent event) {

        repository
                .findByBandId(event.bandId())
                .ifPresentOrElse(
                        privileges -> updateNewPrivilegesAndPropagateChanges(privileges, event),
                        () -> saveNewPrivileges(event)
                );
    }

    private void updateNewPrivilegesAndPropagateChanges(final DefaultBandPrivileges existingPrivileges, final BandDefaultPrivilegesChangedEvent event) {
        if(
                existingPrivileges.isCanSeeCalendarEntryByDefault() == event.canSeeCalendarEntryByDefault() &&
                existingPrivileges.isCanSeeCalendarEntryPaymentByDefault() == event.canSeeCalendarEntryPaymentByDefault() &&
                existingPrivileges.isCanSeeCalendarEntryDetailsByDefault() == event.canSeeCalendarEntryByDefault())
        {
            log.info("Default privileges were not changed");
            return;
        }

        existingPrivileges.setCanSeeCalendarEntryByDefault(event.canSeeCalendarEntryByDefault());
        existingPrivileges.setCanSeeCalendarEntryPaymentByDefault(event.canSeeCalendarEntryPaymentByDefault());
        existingPrivileges.setCanSeeCalendarEntryDetailsByDefault(event.canSeeCalendarEntryDetailsByDefault());

        repository.save(existingPrivileges);

        final var events = repository
                .findAllMemberEntryRelationsWithoutRestriction(event.bandId())
                .stream()
                .map(entryIdWithMemberIdDTO -> EventMapper.toNotificationEvent(event, entryIdWithMemberIdDTO))
                .toList();

        publisher.publishAll(events, PublishingMode.BATCH_ASYNC_WITH_BLOCKING);


    }

    private void saveNewPrivileges(final BandDefaultPrivilegesChangedEvent event) {
        final var entity = DefaultBandPrivileges.builder()
                .bandId(event.bandId())
                .canSeeCalendarEntryByDefault(event.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(event.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(event.canSeeCalendarEntryDetailsByDefault())
                .build();

        repository.save(entity);
    }
}
