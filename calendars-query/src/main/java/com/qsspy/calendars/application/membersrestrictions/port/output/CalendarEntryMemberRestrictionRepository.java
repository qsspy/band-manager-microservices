package com.qsspy.calendars.application.membersrestrictions.port.output;

import com.qsspy.calendars.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;

import java.util.List;
import java.util.UUID;

public interface CalendarEntryMemberRestrictionRepository {

    /**
     * Fetches all calendar entries and member restrictions correlated with them
     *
     * @param bandId band identifier
     * @return list of restrictions
     */
    List<CalendarEntryMemberRestrictionDTO> getMemberRestrictions(final UUID bandId);
}
