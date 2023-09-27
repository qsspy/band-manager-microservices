package com.qsspy.bands.infrastructure.dataview.bandmemberprivilegebybandiduserid;

import com.qsspy.bands.application.userprivileges.port.output.dto.BandMemberPrivilegesDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static BandMemberPrivilegesDTO toDto(final BandMemberPrivilegeByBandIdUserId entity) {
        return BandMemberPrivilegesDTO.builder()
                .canAccessCalendar(entity.isCanAccessCalendar())
                .canAddCalendarEntries(entity.isCanAddCalendarEntries())
                .canEditCalendarEntries(entity.isCanEditCalendarEntries())
                .canDeleteCalendarEntries(entity.isCanDeleteCalendarEntries())
                .canAccessFinanceHistory(entity.isCanAccessFinanceHistory())
                .canAddFinanceEntries(entity.isCanAddFinanceEntries())
                .canSeeFinanceIncomeEntries(entity.isCanSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(entity.isCanSeeFinanceOutcomeEntries())
                .build();
    }
}
