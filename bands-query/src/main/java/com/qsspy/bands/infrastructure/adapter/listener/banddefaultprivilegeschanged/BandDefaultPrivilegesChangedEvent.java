package com.qsspy.bands.infrastructure.adapter.listener.banddefaultprivilegeschanged;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandDefaultPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,
        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,
        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries,
        boolean canSeeCalendarEntryByDefault,
        boolean canSeeCalendarEntryPaymentByDefault,
        boolean canSeeCalendarEntryDetailsByDefault
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "band.default.privileges.changed";
    static final int EVENT_VERSION = 1;

}