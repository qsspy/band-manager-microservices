package com.qsspy.calendars.domain.calendar.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate
) implements DomainEvent { }