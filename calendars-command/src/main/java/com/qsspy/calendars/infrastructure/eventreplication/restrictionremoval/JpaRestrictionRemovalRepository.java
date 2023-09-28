package com.qsspy.calendars.infrastructure.eventreplication.restrictionremoval;

import com.qsspy.calendars.domain.calendar.RestrictedCalendarViewerPrivileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface JpaRestrictionRemovalRepository extends JpaRepository<RestrictedCalendarViewerPrivileges, Void> {

    void deleteAllById_MemberId(final UUID memberId);

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.restrictionremoval.CalendarEntryIdDTO(
                c.id.value,
                c.eventDate.value
           )
           FROM CALENDAR_ENTRIES c
           WHERE c.bandId.value = :bandId
           """)
    List<CalendarEntryIdDTO> findAllCalendarEntryIdsByBandId(final UUID bandId);
}
