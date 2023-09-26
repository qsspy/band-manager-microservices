package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberprivilegeschanged;

import com.qsspy.authservice.domain.memberprivileges.BandMemberPrivilegesFactory;
import com.qsspy.authservice.domain.memberprivileges.dto.BandMemberPrivilegesData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Transactional
class BandMemberPrivilegesChangedEventProcessor {

    private final BandMemberPrivilegesSaveRepository repository;

    void process(final BandMemberPrivilegesChangedEvent event) {
        final var privilegesData = BandMemberPrivilegesData.builder()
                .bandId(event.bandId())
                .memberId(event.memberId())

                .canAccessCalendar(event.canAccessCalendar())
                .canAddCalendarEntries(event.canAddCalendarEntries())
                .canEditCalendarEntries(event.canEditCalendarEntries())
                .canDeleteCalendarEntries(event.canDeleteCalendarEntries())
                .canAccessFinanceHistory(event.canAccessFinanceHistory())
                .canAddFinanceEntries(event.canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries())
                .build();

        final var domainEntity = BandMemberPrivilegesFactory.instantiatePrivileges(privilegesData);
        repository.save(domainEntity);
    }
}
