package com.qsspy.gateway.calendars.query;

import com.qsspy.commons.architecture.cqrs.QueryResult;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
record GetCalendarEntryDetailsQueryResponse(
        @Nullable
        String address,
        @Nullable
        Integer eventDurationHours,
        @Nullable
        String description
) implements QueryResult { }
