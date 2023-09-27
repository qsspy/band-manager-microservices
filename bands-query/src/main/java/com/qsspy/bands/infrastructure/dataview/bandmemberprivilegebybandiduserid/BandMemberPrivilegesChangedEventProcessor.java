package com.qsspy.bands.infrastructure.dataview.bandmemberprivilegebybandiduserid;

import com.qsspy.bands.infrastructure.adapter.listener.bandmemberprivilegeschanged.BandMemberPrivilegesChangedEvent;
import com.qsspy.commons.architecture.eda.DataPropagationEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class BandMemberPrivilegesChangedEventProcessor implements DataPropagationEventProcessor<BandMemberPrivilegesChangedEvent> {

    private final BandMemberPrivilegeByBandIdUserIdCassandraRepository repository;

    @Override
    public void process(final BandMemberPrivilegesChangedEvent event) {

        final var entity = BandMemberPrivilegeByBandIdUserId.builder()
                .key(new BandMemberPrivilegeByBandIdUserIdKey(event.bandId(), event.memberId()))
                .canAccessCalendar(event.canAccessCalendar())
                .canAddCalendarEntries(event.canAddCalendarEntries())
                .canEditCalendarEntries(event.canEditCalendarEntries())
                .canDeleteCalendarEntries(event.canDeleteCalendarEntries())
                .canAccessFinanceHistory(event.canAccessFinanceHistory())
                .canAddFinanceEntries(event.canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries())
                .build();

        repository.save(entity);
    }
}
