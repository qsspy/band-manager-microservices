package com.qsspy.calendars.application.entries.details.port.input;

import com.qsspy.commons.architecture.cqrs.QueryHandler;

public interface  GetCalendarEntryDetailsQueryHandler extends QueryHandler<GetCalendarEntryDetailsQuery, GetCalendarEntryDetailsQueryResult> {
}
