package com.qsspy.bands.domain.band;

import com.qsspy.bands.domain.band.event.*;
import com.qsspy.bands.domain.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DomainEventFactory {

    static BandCreatedEvent buildBandCreatedEvent(final UUID adminId, final UUID bandId) {
        return BandCreatedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .adminId(adminId)
                .bandId(bandId)
                .build();
    }

    static BandMemberAddedEvent buildBandMemberAddedEvent(final User user, final UUID bandId) {
        return BandMemberAddedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .memberId(user.getId())
                .memberEmail(user.getEmail())
                .memberFirstName(user.getFirstName())
                .memberLastName(user.getLastName())
                .bandId(bandId)
                .build();
    }

    static BandMemberRemovedEvent buildBandMemberRemovedEvent(final UUID memberId, final String memberEmail, final UUID bandId) {
        return BandMemberRemovedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .memberId(memberId)
                .memberEmail(memberEmail)
                .bandId(bandId)
                .build();
    }

    static BandMemberPrivilegesChangedEvent buildBandMemberPrivilegesChangedEvent(final BandMemberPrivileges privileges) {
        return BandMemberPrivilegesChangedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .bandId(privileges.getId().getBandId())
                .memberId(privileges.getId().getMemberId())
                .canAccessCalendar(privileges.getCanAccessCalendar().isAllowed())
                .canAddCalendarEntries(privileges.getCanAddCalendarEntries().isAllowed())
                .canEditCalendarEntries(privileges.getCanEditCalendarEntries().isAllowed())
                .canDeleteCalendarEntries(privileges.getCanDeleteCalendarEntries().isAllowed())
                .canAccessFinanceHistory(privileges.getCanAccessFinanceHistory().isAllowed())
                .canAddFinanceEntries(privileges.getCanAddFinanceEntries().isAllowed())
                .canSeeFinanceIncomeEntries(privileges.getCanSeeFinanceIncomeEntries().isAllowed())
                .canSeeFinanceOutcomeEntries(privileges.getCanSeeFinanceOutcomeEntries().isAllowed())
                .build();
    }

    static BandDefaultPrivilegesChangedEvent buildBandDefaultPrivilegesChangedEvent(final DefaultBandPrivileges privileges) {
        return BandDefaultPrivilegesChangedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(now())

                .bandId(privileges.getId().getValue())
                .canAccessCalendar(privileges.getCanAccessCalendar().isAllowed())
                .canAddCalendarEntries(privileges.getCanAddCalendarEntries().isAllowed())
                .canEditCalendarEntries(privileges.getCanEditCalendarEntries().isAllowed())
                .canDeleteCalendarEntries(privileges.getCanDeleteCalendarEntries().isAllowed())
                .canAccessFinanceHistory(privileges.getCanAccessFinanceHistory().isAllowed())
                .canAddFinanceEntries(privileges.getCanAddFinanceEntries().isAllowed())
                .canSeeFinanceIncomeEntries(privileges.getCanSeeFinanceIncomeEntries().isAllowed())
                .canSeeFinanceOutcomeEntries(privileges.getCanSeeFinanceOutcomeEntries().isAllowed())
                .canSeeCalendarEntryByDefault(privileges.getCanSeeCalendarEntryByDefault().isAllowed())
                .canSeeCalendarEntryPaymentByDefault(privileges.getCanSeeCalendarEntryPaymentByDefault().isAllowed())
                .canSeeCalendarEntryDetailsByDefault(privileges.getCanSeeCalendarEntryDetailsByDefault().isAllowed())
                .build();
    }

    static private long now() {
        return Instant.now().toEpochMilli();
    }
}
