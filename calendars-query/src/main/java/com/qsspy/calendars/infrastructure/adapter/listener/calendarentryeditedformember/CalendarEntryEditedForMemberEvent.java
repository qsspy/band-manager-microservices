package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryeditedformember;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryEditedForMemberEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        UUID memberId,
        LocalDateTime oldEventDate,
        LocalDateTime eventDate,
        EventKind eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "calendar.entry.for.member.edited";
    static final int EVENT_VERSION = 1;
}
