package com.qsspy.bands.domain.band.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberPrivilegesChangedEvent(
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
) implements DomainEvent { }