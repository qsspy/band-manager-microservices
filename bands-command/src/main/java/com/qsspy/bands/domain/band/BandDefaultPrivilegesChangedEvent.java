package com.qsspy.bands.domain.band;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandDefaultPrivilegesChangedEvent(
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
) implements DomainEvent {

    static final String EVENT_TYPE = "band.default.privileges.changed";
    static final int EVENT_VERSION = 1;

    @Override
    public String eventType() {
        return EVENT_TYPE;
    }

    @Override
    public int eventVersion() {
        return EVENT_VERSION;
    }

    @Override
    public String destinationBindingName() {
        return "band-default-privileges-changed";
    }

    @Override
    public Object partitionKey() {
        return bandId;
    }
}