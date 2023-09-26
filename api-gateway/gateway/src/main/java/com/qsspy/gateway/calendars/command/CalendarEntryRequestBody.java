package com.qsspy.gateway.calendars.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

record CalendarEntryRequestBody(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime eventDate,
        String eventKind,
        BigDecimal amount,
        @Nullable
        String address,
        @Nullable
        Integer eventDurationHours,
        @Nullable
        String description
) { }
