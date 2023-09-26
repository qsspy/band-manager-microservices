package com.qsspy.authservice.domain.memberprivileges;

import com.qsspy.authservice.domain.memberprivileges.dto.BandMemberPrivilegesData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandMemberPrivilegesFactory {

    public static BandMemberPrivileges instantiatePrivileges(final BandMemberPrivilegesData data) {
        final var privileges = BandMemberPrivileges.builder()
                .id(new BandMemberPrivilegesId(data.bandId(), data.memberId()))
                .canAccessCalendar(new CanAccessCalendarPrivilege(data.canAccessCalendar()))
                .canAddCalendarEntries(new CanAddCalendarEntriesPrivilege(data.canAddCalendarEntries()))
                .canEditCalendarEntries(new CanEditCalendarEntriesPrivilege(data.canEditCalendarEntries()))
                .canDeleteCalendarEntries(new CanDeleteCalendarEntriesPrivilege(data.canDeleteCalendarEntries()))
                .canAccessFinanceHistory(new CanAccessFinanceHistoryPrivilege(data.canAccessFinanceHistory()))
                .canAddFinanceEntries(new CanAddFinanceEntriesPrivilege(data.canAddFinanceEntries()))
                .canSeeFinanceIncomeEntries(new CanSeeFinanceIncomeEntriesPrivilege(data.canSeeFinanceIncomeEntries()))
                .canSeeFinanceOutcomeEntries(new CanSeeFinanceOutcomeEntriesPrivilege(data.canSeeFinanceOutcomeEntries()))
                .build();

        privileges.validateCurrentState();
        return privileges;
    }
}
