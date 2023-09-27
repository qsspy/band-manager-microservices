package com.qsspy.calendars.application.entries.list.port.input;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetCalendarEntryListQueryResult(
        List<ListItem> entries
) implements QueryResult {

        @Builder
        public record ListItem(
                UUID id,
                EventKind eventKind,
                LocalDateTime eventDate,
                @Nullable
                BigDecimal amount,
                boolean canSeeDetails
        ) {}
}
