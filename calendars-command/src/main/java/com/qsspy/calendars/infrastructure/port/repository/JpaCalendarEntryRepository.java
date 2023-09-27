package com.qsspy.calendars.infrastructure.port.repository;

import com.qsspy.calendars.domain.calendar.CalendarEntry;
import com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction.CalendarEntryPrivilegesView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaCalendarEntryRepository extends JpaRepository<CalendarEntry, UUID> {

    void deleteByBandIdValueAndIdValue(final UUID bandId, final UUID entryId);

    Optional<CalendarEntry> findByBandIdValueAndIdValue(final UUID bandId, final UUID entryId);

    @Query("""
           SELECT new com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction.CalendarEntryPrivilegesView(
                c.id.value,
                c.eventKind,
                c.eventDate.value
           )
           FROM CALENDAR_ENTRIES c
           WHERE c.bandId.value = :bandId
           """)
    List<CalendarEntryPrivilegesView> getViewsByBandId(final UUID bandId);
}
