package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CalendarEntryDetailsByBandIdCassandraRepository extends CassandraRepository<CalendarEntryDetailsByBandId, CalendarEntryDetailsByBandId.Id> {

}
