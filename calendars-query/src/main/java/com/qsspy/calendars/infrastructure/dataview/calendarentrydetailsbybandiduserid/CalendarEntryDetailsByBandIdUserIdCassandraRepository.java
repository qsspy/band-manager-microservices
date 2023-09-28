package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
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

    @Query("""
            UPDATE calendar_entry_details_by_band_id_user_id_event_id
            SET is_visible = :isVisible
            WHERE band_id = :bandId
                 AND user_id = :userId
                 AND entry_id = :entryId
            """)
    void updateEntryDetailsViewPrivilege(
            @Param("isVisible") final boolean isVisible,
            @Param("bandId") final UUID bandId,
            @Param("userId") final UUID userId,
            @Param("entryId") final UUID entryId
    );

    Optional<CalendarEntryDetailsByBandIdUserId> findByKey_BandIdAndKey_UserIdAndKey_EventIdAndIsVisible(final UUID bandId, final UUID userId, final UUID eventId, final boolean isVisible);

}