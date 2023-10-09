package com.qsspy.calendars.application.entry.create;

import com.qsspy.calendars.application.entry.common.port.output.CalendarEntrySaveRepository;
import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommand;
import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommandHandler;
import com.qsspy.calendars.domain.calendar.CalendarEntryFactory;
import com.qsspy.commons.port.output.publisher.domain.DomainEventPublisher;
import com.qsspy.commons.port.output.publisher.notification.MeasurementNotificationEvent;
import com.qsspy.commons.port.output.publisher.notification.MeasurementType;
import com.qsspy.commons.port.output.publisher.notification.NotificationEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
class CreateCalendarEntryCommandHandlerImpl implements CreateCalendarEntryCommandHandler {

    private final CalendarEntrySaveRepository saveRepository;
    private final DomainEventPublisher publisher;
    private final NotificationEventPublisher notificationEventPublisher;

    @Override
    public void handle(final CreateCalendarEntryCommand command) {
        command.validate();

        if(!command.initiatorsBandId().equals(command.bandId())) {
            throw new IllegalStateException("Member can only add entries to its own band!");
        }

        final var specification = CommandToDtoMapper.toSpecification(command);
        final var entry = CalendarEntryFactory.createNewCalendarEntry(specification);
        saveRepository.save(entry);
        notificationEventPublisher.publish(new MeasurementNotificationEvent(
                UUID.randomUUID(),
                Instant.now().toEpochMilli(),
                MeasurementType.CALENDAR_DATA_REPLICATED
        ));
        publisher.publishAll(entry.flushEvents());
    }
}
