package com.qsspy.gateway.calendars.query;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

record GetCalendarEntryListQueryResponse(List<ListItem> entries) {

        @Builder
        public record ListItem(
                UUID id,
                String eventKind,
                LocalDateTime eventDate,
                @Nullable
                BigDecimal amount,
                boolean canSeeDetails
        ) {}
}
