package com.qsspy.calendars.application.entry.restriction;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommandHandler;
import com.qsspy.calendars.domain.calendar.CalendarEntry;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class RestrictMemberPrivilegesForEntryCommandHandlerImpl implements RestrictMemberPrivilegesForEntryCommandHandler {

    private final CalendarEntryGetRepository getRepository;
    private final CalendarEntrySaveRepository saveRepository;
    private final DomainEventPublisher publisher;

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
        publisher.publishAll(entry.flushEvents());
    }
}
