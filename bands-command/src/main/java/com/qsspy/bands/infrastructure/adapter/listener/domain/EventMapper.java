package com.qsspy.bands.infrastructure.adapter.listener.domain;

import com.qsspy.bands.domain.band.event.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {

    static BandCreatedNotificationEvent fromDomainEvent(final BandCreatedEvent event) {
        return BandCreatedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .adminId(event.adminId())
                .bandId(event.bandId())
                .build();
    }

    static BandDefaultPrivilegesChangedNotificationEvent fromDomainEvent(final BandDefaultPrivilegesChangedEvent event) {
        return BandDefaultPrivilegesChangedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .bandId(event.bandId())
                .canAccessCalendar(event.canAccessCalendar())
                .canAddCalendarEntries(event.canAddCalendarEntries())
                .canEditCalendarEntries(event.canEditCalendarEntries())
                .canDeleteCalendarEntries(event.canDeleteCalendarEntries())
                .canAccessFinanceHistory(event.canAccessFinanceHistory())
                .canAddFinanceEntries(event.canAddFinanceEntries())
                .canSeeFinanceIncomeEntries(event.canSeeFinanceIncomeEntries())
                .canSeeFinanceOutcomeEntries(event.canSeeFinanceOutcomeEntries())
                .canSeeCalendarEntryByDefault(event.canSeeCalendarEntryByDefault())
                .canSeeCalendarEntryPaymentByDefault(event.canSeeCalendarEntryPaymentByDefault())
                .canSeeCalendarEntryDetailsByDefault(event.canSeeCalendarEntryDetailsByDefault())
                .build();
    }

    static BandMemberAddedNotificationEvent fromDomainEvent(final BandMemberAddedEvent event) {
        return BandMemberAddedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .memberId(event.memberId())
                .memberEmail(event.memberEmail())
                .memberFirstName(event.memberFirstName())
                .memberLastName(event.memberLastName())
                .bandId(event.bandId())
                .build();
    }

    static BandMemberPrivilegesChangedNotificationEvent fromDomainEvent(final BandMemberPrivilegesChangedEvent event) {
        return BandMemberPrivilegesChangedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

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
    }

    static BandMemberRemovedNotificationEvent fromDomainEvent(final BandMemberRemovedEvent event) {
        return BandMemberRemovedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .memberId(event.memberId())
                .bandId(event.bandId())
                .memberEmail(event.memberEmail())
                .build();
    }
}
