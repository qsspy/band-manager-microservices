package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CalendarEntryByBandIdUserIdCassandraRepository extends CassandraRepository<CalendarEntryByBandIdUserId, CalendarEntryByBandIdUserId.Id> {
}
