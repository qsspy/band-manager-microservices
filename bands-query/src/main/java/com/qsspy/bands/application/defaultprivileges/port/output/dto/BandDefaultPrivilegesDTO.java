package com.qsspy.bands.application.defaultprivileges.port.output.dto;

import lombok.Builder;

@Builder
public record BandDefaultPrivilegesDTO(
        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,

        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,

        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries,

        boolean canSeeCalendarEntryByDefault,
        boolean canSeeCalendarEntryPaymentByDefault,
        boolean canSeeCalendarEntryDetailsByDefault
) {
}
