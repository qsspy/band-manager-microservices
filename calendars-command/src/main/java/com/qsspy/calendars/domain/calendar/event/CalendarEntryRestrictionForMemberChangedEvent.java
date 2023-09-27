package com.qsspy.calendars.domain.calendar.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryRestrictionForMemberChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID entryId,
        UUID bandId,
        LocalDateTime eventDate,

        boolean isVisible,
        boolean isVisibleDetails,
        boolean isVisiblePaymentAmount
) implements DomainEvent { }