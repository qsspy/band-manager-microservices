package com.qsspy.calendars.application.entry.common.port.output;

import com.qsspy.calendars.domain.calendar.CalendarEntry;

public interface CalendarEntrySaveRepository {

    /**
     * Saves entry to the repository
     *
     * @param entry entry to be saved
     */
    void save(final CalendarEntry entry);
}
