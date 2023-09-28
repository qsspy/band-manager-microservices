package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.infrastructure.eventreplication.DefaultBandPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Query("""
           SELECT b.id.memberId
           FROM BAND_MEMBER b
           WHERE b.id.bandId = :bandId
           """)
    Set<UUID> findBandMemberIds(final UUID bandId);

    Optional<DefaultBandPrivileges> findByBandId(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry.EntryIdWithMemberIdDTO(
                e.id.value,
                e.eventDate.value,
                b.id.memberId
           )
           FROM CALENDAR_ENTRIES e
           INNER JOIN BAND_MEMBER b ON b.id.bandId = e.bandId.value
           LEFT JOIN RESTRICTED_CALENDAR_ENTRY_VIEWER_PRIVILEGES ep ON ep.id.memberId = b.id.memberId AND ep.id.entryId = e.id.value
           WHERE e.bandId.value = :bandId
                AND ep.id.entryId IS NULL
           """)
    List<EntryIdWithMemberIdDTO> findAllMemberEntryRelationsWithoutRestriction(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry.CalendarEntryWithPrivilegesDTO(
                c.id.value,
                c.bandId.value,
                c.eventDate.value,
                c.eventKind,
                c.amount.value,
                c.address.fullAddress,
                c.eventDuration.value,
                c.description.text,
                p.canSeeCalendarEntryByDefault,
                p.canSeeCalendarEntryDetailsByDefault,
                p.canSeeCalendarEntryPaymentByDefault
           )
           FROM CALENDAR_ENTRIES c
           INNER JOIN DEFAULT_BAND_PRIVILEGES p ON p.bandId = c.bandId.value
           WHERE c.bandId.value = :bandId
           """)
    List<CalendarEntryWithPrivilegesDTO> findAllCalendarEntriesWithDefaultPrivileges(final UUID bandId);

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry.CalendarEntryIdDTO(
                c.id.value,
                c.eventDate.value
           )
           FROM CALENDAR_ENTRIES c
           WHERE c.bandId.value = :bandId
           """)
    List<CalendarEntryIdDTO> findAllCalendarEntryIdsByBandId(final UUID bandId);
}
