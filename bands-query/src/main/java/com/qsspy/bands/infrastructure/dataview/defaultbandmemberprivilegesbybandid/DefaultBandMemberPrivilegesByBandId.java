package com.qsspy.bands.infrastructure.dataview.defaultbandmemberprivilegesbybandid;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("default_band_member_privileges_by_band_id")
@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
class DefaultBandMemberPrivilegesByBandId {

    @PrimaryKey(value = "band_id")
    private UUID bandId;

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

    @Column("can_see_calendar_entry_by_default")
    boolean canSeeCalendarEntryByDefault;

    @Column("can_see_calendar_entry_payment_by_default")
    boolean canSeeCalendarEntryPaymentByDefault;

    @Column("can_see_calendar_entry_details_by_default")
    boolean canSeeCalendarEntryDetailsByDefault;
}
