package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface CalendarEntryByBandIdCassandraRepository extends CassandraRepository<CalendarEntryByBandId, CalendarEntryByBandId.Id> {

    List<CalendarEntryByBandId> findByKey_BandId(final UUID bandId);
}
