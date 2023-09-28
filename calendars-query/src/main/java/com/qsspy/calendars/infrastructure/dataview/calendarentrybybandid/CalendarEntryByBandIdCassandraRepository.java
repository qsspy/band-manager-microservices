package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CalendarEntryByBandIdCassandraRepository extends CassandraRepository<CalendarEntryByBandId, CalendarEntryByBandId.Id> {
}
