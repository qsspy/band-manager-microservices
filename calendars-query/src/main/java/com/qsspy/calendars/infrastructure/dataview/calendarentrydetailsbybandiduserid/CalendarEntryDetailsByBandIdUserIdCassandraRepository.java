package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CalendarEntryDetailsByBandIdUserIdCassandraRepository extends CassandraRepository<CalendarEntryDetailsByBandIdUserId, CalendarEntryDetailsByBandIdUserId.Id> {
}
