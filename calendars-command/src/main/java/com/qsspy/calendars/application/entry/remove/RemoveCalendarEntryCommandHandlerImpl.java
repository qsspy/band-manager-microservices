package com.qsspy.calendars.application.entry.remove;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntryGetRepository;
import com.qsspy.calendars.application.entry.remove.port.input.RemoveCalendarEntryCommand;
import com.qsspy.calendars.application.entry.remove.port.input.RemoveCalendarEntryCommandHandler;
import com.qsspy.calendars.application.entry.remove.port.output.CalendarEntryDeleteRepository;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRemovedEvent;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
class RemoveCalendarEntryCommandHandlerImpl implements RemoveCalendarEntryCommandHandler {

    private final CalendarEntryDeleteRepository deleteRepository;
    private final CalendarEntryGetRepository getRepository;
    private final DomainEventPublisher publisher;

    @Override
    public void handle(final RemoveCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only delete entries from its own band!");
        }

        getRepository.findByBandIdAndId(command.bandId(), command.entryId())
                        .ifPresent(entry -> {
                            final var event = buildCalendarEntryRemovedEvent(command, entry.getEventDateValue());
                            deleteRepository.remove(command.bandId(), command.entryId());
                            publisher.publish(event);
                        });
    }

    private CalendarEntryRemovedEvent buildCalendarEntryRemovedEvent(final RemoveCalendarEntryCommand command, final LocalDateTime eventDate) {
        return CalendarEntryRemovedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .entryId(command.entryId())
                .bandId(command.bandId())
                .eventDate(eventDate)
                .build();
    }
}
