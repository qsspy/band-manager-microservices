package com.qsspy.calendars.application.entry.edit;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.edit.port.input.EditCalendarEntryCommand;
import com.qsspy.calendars.application.entry.edit.port.input.EditCalendarEntryCommandHandler;
import com.qsspy.calendars.domain.calendar.CalendarEntry;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class EditCalendarEntryCommandHandlerImpl implements EditCalendarEntryCommandHandler {

    private final CalendarEntryGetRepository getRepository;
    private final CalendarEntrySaveRepository saveRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final EditCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only edit entries from its own band!");
        }

        getRepository
                .findByBandIdAndId(command.bandId(), command.entryId())
                .ifPresentOrElse(
                        entry -> editEntryAndSave(entry, command),
                        () -> { throw new IllegalStateException("Could not find band for editing the entry"); }
                );
    }

    private void editEntryAndSave(final CalendarEntry entry, final EditCalendarEntryCommand command) {
        final var editData = CommandToDtoMapper.toEditData(command);
        entry.editEntry(editData);
        saveRepository.save(entry);
        publisher.publishAll(entry.flushEvents());
    }
}
