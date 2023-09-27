package com.qsspy.bands.infrastructure.dataview.bandmemberprivilegebybandiduserid;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("band_member_privilege_by_band_id_user_id")
@Builder
@Getter
class BandMemberPrivilegeByBandIdUserId {

    @PrimaryKey
    private BandMemberPrivilegeByBandIdUserIdKey key;

    @Column("can_access_calendar")
    boolean canAccessCalendar;

    @Column("can_add_calendar_entries")
    boolean canAddCalendarEntries;

    @Column("can_edit_calendar_entries")
    boolean canEditCalendarEntries;

    @Column("can_delete_calendar_entries")
    boolean canDeleteCalendarEntries;

    @Column("can_access_finance_history")
    boolean canAccessFinanceHistory;

    @Column("can_add_finance_entries")
    boolean canAddFinanceEntries;

    @Column("can_see_finance_income_entries")
    boolean canSeeFinanceIncomeEntries;

    @Column("can_see_finance_outcome_entries")
    boolean canSeeFinanceOutcomeEntries;
}
