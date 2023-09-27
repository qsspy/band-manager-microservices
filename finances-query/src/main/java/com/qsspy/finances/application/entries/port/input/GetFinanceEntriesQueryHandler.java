package com.qsspy.finances.application.entries.port.input;

import com.qsspy.commons.architecture.cqrs.QueryHandler;

public interface GetFinanceEntriesQueryHandler extends QueryHandler<GetFinanceEntriesQuery, GetFinanceEntriesQueryResult> {
}
