package com.qsspy.gateway.bands.command;

import org.springframework.lang.Nullable;

record ChangeBandPrivilegesRequestBody(
        @Nullable
        Boolean canAccessCalendar,
        @Nullable
        Boolean canAddCalendarEntries,
        @Nullable
        Boolean canEditCalendarEntries,
        @Nullable
        Boolean canDeleteCalendarEntries,
        @Nullable
        Boolean canAccessFinanceHistory,
        @Nullable
        Boolean canAddFinanceEntries,
        @Nullable
        Boolean canSeeFinanceIncomeEntries,
        @Nullable
        Boolean canSeeFinanceOutcomeEntries,
        @Nullable
        Boolean canSeeCalendarEntryByDefault,
        @Nullable
        Boolean canSeeCalendarEntryPaymentByDefault,
        @Nullable
        Boolean canSeeCalendarEntryDetailsByDefault
) {
}
