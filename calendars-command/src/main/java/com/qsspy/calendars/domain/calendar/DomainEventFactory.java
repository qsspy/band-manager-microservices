package com.qsspy.calendars.domain.calendar;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRestrictionForMemberChangedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DomainEventFactory {

    static CalendarEntryRestrictionForMemberChangedEvent buildCalendarEntryRestrictionForMemberChangedEvent(final RestrictedCalendarViewerPrivileges restriction, final CalendarEntry entry) {
        return CalendarEntryRestrictionForMemberChangedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .memberId(restriction.getMemberId())
                .entryId(entry.getId().getValue())
                .bandId(entry.getBandId().getValue())
                .eventDate(entry.getEventDate().getValue())
                .isVisible(restriction.getCanSeeCalendarEntry().isAllowed())
                .isVisibleDetails(restriction.getCanSeeCalendarEntryDetails().isAllowed())
                .isVisiblePaymentAmount(restriction.getCanSeeCalendarEntryPayment().isAllowed())
                .build();
    }

    static CalendarEntryAddedEvent buildCalendarEntryAddedEvent(final CalendarEntry entry) {
        return CalendarEntryAddedEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .eventId(entry.getId().getValue())
                .bandId(entry.getBandId().getValue())
                .eventDate(entry.getEventDate().getValue())
                .eventKind(entry.getEventKind())
                .amount(entry.getAmount().getValue())
                .address(entry.getAddress() != null ? entry.getAddress().getFullAddress() : null)
                .eventDuration(entry.getEventDuration() != null ? entry.getEventDuration().getValue() : null)
                .description(entry.getDescription() != null ? entry.getDescription().getText() : null)
                .build();
    }
}
