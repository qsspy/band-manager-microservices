package com.qsspy.bands.infrastructure.dataview.defaultbandmemberprivilegesbybandid;

import com.qsspy.bands.application.defaultprivileges.port.output.dto.BandDefaultPrivilegesDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static BandDefaultPrivilegesDTO toDto(final DefaultBandMemberPrivilegesByBandId entity) {
        return BandDefaultPrivilegesDTO.builder()
                .canAccessCalendar(entity.isCanAccessCalendar())
                .canAddCalendarEntries(entity.isCanAddCalendarEntries())
                .canEditCalendarEntries(entity.isCanEditCalendarEntries())
                .canDeleteCalendarEntries(entity.isCanDeleteCalendarEntries())
                .canAccessFinanceHistory(entity.isCanAccessFinanceHistory())
                .canAddFinanceEntries(entity.isCanAddFinanceEntries())
                .canSeeFinanceIncomeEntries(entity.isCanSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(entity.isCanSeeFinanceOutcomeEntries())
                .canSeeCalendarEntryByDefault(entity.isCanSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(entity.isCanSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(entity.isCanSeeCalendarEntryDetailsByDefault())
                .build();
    }
}
