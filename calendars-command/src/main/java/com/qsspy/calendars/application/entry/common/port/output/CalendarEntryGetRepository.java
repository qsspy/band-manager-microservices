package com.qsspy.calendars.application.entry.common.port.output;


import com.qsspy.calendars.domain.calendar.CalendarEntry;

import java.util.Optional;
import java.util.UUID;

public interface CalendarEntryGetRepository {

    /**
     * Fetches calendar entry from repository
     *
     * @param bandId id of a band that owns entry
     * @param entryId entry id to be fetched
     * @return calendar entry is exists
     */
    Optional<CalendarEntry> findByBandIdAndId(final UUID bandId, final UUID entryId);
}
