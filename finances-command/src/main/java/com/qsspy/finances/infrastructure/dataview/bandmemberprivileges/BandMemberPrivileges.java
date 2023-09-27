package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Entity(name = "BAND_MEMBER_PRIVILEGES")
class BandMemberPrivileges {

    @EmbeddedId
    private BandMemberPrivilegesId id;

    @Column(name = "CAN_SEE_FINANCE_INCOME_ENTRIES")
    private boolean canSeeFinanceIncomeEntries;

    @Column(name = "CAN_SEE_FINANCE_OUTCOME_ENTRIES")
    private boolean canSeeFinanceOutcomeEntries;
}
