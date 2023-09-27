package com.qsspy.calendars.application.entry.create;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommand;
import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommandHandler;
import com.qsspy.calendars.domain.calendar.CalendarEntryFactory;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
class CreateCalendarEntryCommandHandlerImpl implements CreateCalendarEntryCommandHandler {

    private final CalendarEntrySaveRepository saveRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final CreateCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only add entries to its own band!");
        }

        final var specification = CommandToDtoMapper.toSpecification(command);
        final var entry = CalendarEntryFactory.createNewCalendarEntry(specification);
        saveRepository.save(entry);
        publisher.publishAll(entry.flushEvents());
    }
}
