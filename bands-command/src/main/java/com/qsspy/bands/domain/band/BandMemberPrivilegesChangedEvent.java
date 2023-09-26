package com.qsspy.bands.domain.band;

import com.qsspy.commons.port.output.publisher.DomainEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID memberId,

        boolean canAccessCalendar,
        boolean canAddCalendarEntries,
        boolean canEditCalendarEntries,
        boolean canDeleteCalendarEntries,
        boolean canAccessFinanceHistory,
        boolean canAddFinanceEntries,
        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries
) implements DomainEvent {

    static final String EVENT_TYPE = "band.member.privileges.changed";
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
        return "band-member-privileges-changed";
    }

    @Override
    public Object partitionKey() {
        return bandId;
    }
}