package com.qsspy.calendars.application.entry.restriction;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommandHandler;
import com.qsspy.calendars.domain.calendar.CalendarEntry;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class RestrictMemberPrivilegesForEntryCommandHandlerImpl implements RestrictMemberPrivilegesForEntryCommandHandler {

    private final CalendarEntryGetRepository getRepository;
    private final CalendarEntrySaveRepository saveRepository;
    private final DomainEventPublisher publisher;
    private final NotificationEventPublisher notificationEventPublisher;

    @Override
    public void handle(final RestrictMemberPrivilegesForEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Admin cannot edit his own privileges!");
        }

        getRepository
                .findByBandIdAndId(command.bandId(), command.entryId())
                .ifPresentOrElse(
                        entry -> editMemberPrivilegesAndSave(entry, command),
                        () -> { throw new IllegalStateException("Could not find band for editing the member entry privileges"); }
                );
    }

    private void editMemberPrivilegesAndSave(final CalendarEntry entry, final RestrictMemberPrivilegesForEntryCommand command) {
        final var editData = CommandToDtoMapper.toDomainData(command);
        entry.editMemberEntryPrivileges(editData);
        saveRepository.save(entry);
        notificationEventPublisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
        publisher.publishAll(entry.flushEvents());
    }
}
