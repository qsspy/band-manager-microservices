package com.qsspy.calendars.infrastructure.adapter.listener.calendarentryadded;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryAddedEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
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

    static final String EVENT_TYPE = "calendar.entry.added";
    static final int EVENT_VERSION = 1;
}
