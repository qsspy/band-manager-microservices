package com.qsspy.calendars.domain.calendar.event;

import com.qsspy.calendars.domain.calendar.EventKind;
import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
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