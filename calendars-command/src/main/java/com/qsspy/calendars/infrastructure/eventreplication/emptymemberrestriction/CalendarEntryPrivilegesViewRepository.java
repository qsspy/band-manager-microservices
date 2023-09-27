package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import java.util.List;
import java.util.UUID;

public interface CalendarEntryPrivilegesViewRepository {

    List<CalendarEntryPrivilegesView> getViewsByBandId(final UUID bandId);
}
