package com.qsspy.calendars.application.entries.list.port.input;

import com.qsspy.commons.architecture.cqrs.QueryHandler;

public interface GetCalendarEntryListQueryHandler extends QueryHandler<GetCalendarEntryListQuery, GetCalendarEntryListQueryResult> {
}
