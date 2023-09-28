package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.eventreplication.DefaultBandPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaDefaultBandPrivilegesRepository extends JpaRepository<DefaultBandPrivileges, UUID> {

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry.DefaultCalendarEntryUserPrivilegesDTO(
                m.id.memberId,
                p.canSeeCalendarEntryByDefault,
                p.canSeeCalendarEntryDetailsByDefault,
                p.canSeeCalendarEntryPaymentByDefault
           )
           FROM DEFAULT_BAND_PRIVILEGES p
           INNER JOIN BAND_MEMBER m ON p.bandId = m.id.bandId
           WHERE p.bandId = :bandId
           """)
    List<DefaultCalendarEntryUserPrivilegesDTO> findDefaultCalendarEntryUserPrivileges(final UUID bandId);
}
