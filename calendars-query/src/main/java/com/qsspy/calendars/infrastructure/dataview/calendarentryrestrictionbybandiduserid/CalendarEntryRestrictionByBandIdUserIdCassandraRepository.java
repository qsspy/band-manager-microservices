package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
interface CalendarEntryRestrictionByBandIdUserIdCassandraRepository extends CassandraRepository<CalendarEntryRestrictionByBandIdUserId, CalendarEntryRestrictionByBandIdUserIdKey> {

    void deleteAllByKey_BandIdAndKey_UserId(final UUID bandId, final UUID userId);

    List<CalendarEntryRestrictionByBandIdUserId> findByKey_BandId(final UUID bandId);

    @Query("""
           UPDATE calendar_entry_restriction_by_band_id_user_id
           SET is_visible = :isVisible,
           is_visible_details = :isVisibleDetails,
           is_visible_payment_amount = :isVisiblePaymentAmount
           WHERE band_id = :bandId
                AND user_id = :userId
                AND entry_id = :entryId
                AND creation_time = :creationTime
           """)
    void updateRestrictionPrivileges(
            @Param("bandId") final UUID bandId,
            @Param("userId") final UUID userId,
            @Param("entryId") final UUID entryId,
            @Param("creationTime") final LocalDateTime creationTime,
            @Param("isVisible") final Boolean isVisible,
            @Param("isVisibleDetails") final Boolean isVisibleDetails,
            @Param("isVisiblePaymentAmount") final Boolean isVisiblePaymentAmount
    );
}
