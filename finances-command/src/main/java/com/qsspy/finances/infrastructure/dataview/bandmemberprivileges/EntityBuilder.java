package com.qsspy.finances.infrastructure.dataview.bandmemberprivileges;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import com.qsspy.finances.domain.finances.event.FinanceEntryAddedEvent;
import com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberprivilegeschanged.BandMemberPrivilegesChangedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EntityBuilder {

    static BandMemberPrivileges build(final BandMemberPrivilegesChangedEvent event) {
        return BandMemberPrivileges.builder()
                .id(new BandMemberPrivilegesId(event.bandId(), event.memberId()))

                .canSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries())
                .canSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries())
                .build();
    }
}
