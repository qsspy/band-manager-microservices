package com.qsspy.bands.application.userprivileges.port.output.dto;

import lombok.Builder;

@Builder
public record BandMemberPrivilegesDTO(
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
