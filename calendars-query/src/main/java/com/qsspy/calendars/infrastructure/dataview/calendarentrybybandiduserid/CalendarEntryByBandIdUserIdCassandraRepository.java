package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
interface CalendarEntryByBandIdUserIdCassandraRepository extends CassandraRepository<CalendarEntryByBandIdUserId, CalendarEntryByBandIdUserId.Id> {

    @Query("""
           UPDATE calendar_entry_by_band_id_user_id
           SET event_kind = :eventKind,
           payment_amount = :paymentAmount
           WHERE band_id = :bandId
                AND user_id = :userId
                AND entry_id = :entryId
                AND creation_time = :eventDate
           """)
    void updateEntryData(
            @Param("eventKind") final String eventKind,
            @Param("paymentAmount") final BigDecimal paymentAmount,
            @Param("bandId") final UUID bandId,
            @Param("userId") final UUID userId,
            @Param("eventDate") final LocalDateTime eventDate,
            @Param("entryId") final UUID entryId
    );


}
