package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
interface CalendarEntryDetailsByBandIdUserIdCassandraRepository extends CassandraRepository<CalendarEntryDetailsByBandIdUserId, CalendarEntryDetailsByBandIdUserId.Id> {

    @Query("""
           UPDATE calendar_entry_details_by_band_id_user_id_event_id
           SET address = :address,
           hours_duration = :hoursDuration,
           description = :description
           WHERE band_id = :bandId
                AND user_id = :userId
                AND entry_id = :entryId
           """)
    void updateEntryDetailsData(
            @Param("address") final String address,
            @Param("hoursDuration") final Long hoursDuration,
            @Param("description") final String description,
            @Param("bandId") final UUID bandId,
            @Param("userId") final UUID userId,
            @Param("entryId") final UUID entryId
    );
}
