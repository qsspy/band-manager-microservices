package com.qsspy.calendars.application.entries.details.port.output.dto;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.Duration;

@Builder
public record CalendarEntryDetailsDTO(
        @Nullable
        String address,
        @Nullable
        Duration eventDuration,
        @Nullable
        String description
) { }
