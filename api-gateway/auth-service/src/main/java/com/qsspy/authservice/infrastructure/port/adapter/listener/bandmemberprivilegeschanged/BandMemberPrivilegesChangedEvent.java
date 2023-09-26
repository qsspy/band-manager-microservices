package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberprivilegeschanged;

import com.qsspy.commons.port.output.publisher.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberPrivilegesChangedEvent(
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
) {

    static final String EVENT_TYPE = "band.member.privileges.changed";
    static final int EVENT_VERSION = 1;

}