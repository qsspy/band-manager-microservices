package com.qsspy.authservice.domain.memberprivileges.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberPrivilegesData(
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
}
