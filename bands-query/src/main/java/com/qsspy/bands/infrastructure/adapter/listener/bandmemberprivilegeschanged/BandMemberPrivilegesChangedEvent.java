package com.qsspy.bands.infrastructure.adapter.listener.bandmemberprivilegeschanged;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID memberId,

        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,
        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,
        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "band.member.privileges.changed";
    static final int EVENT_VERSION = 1;

}