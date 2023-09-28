package com.qsspy.calendars.infrastructure.adapter.listener.domain;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryEditedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRemovedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRestrictionForMemberChangedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EventMapper {

    static CalendarEntryRestrictionForMemberChangedNotificationEvent toNotificationEvent(final CalendarEntryRestrictionForMemberChangedEvent event) {
        return CalendarEntryRestrictionForMemberChangedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .memberId(event.memberId())
                .entryId(event.entryId())
                .bandId(event.bandId())
                .eventDate(event.eventDate())

                .isVisible(event.isVisible())
                .isVisibleDetails(event.isVisibleDetails())
                .isVisiblePaymentAmount(event.isVisiblePaymentAmount())
                .build();
    }

    static CalendarEntryAddedNotificationEvent toNotificationEvent(final CalendarEntryAddedEvent event) {
        return CalendarEntryAddedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .eventDate(event.eventDate())
                .eventKind(event.eventKind())
                .amount(event.amount())
                .address(event.address())
                .eventDuration(event.eventDuration())
                .description(event.description())
                .build();
    }

    static CalendarEntryEditedNotificationEvent toNotificationEvent(final CalendarEntryEditedEvent event) {
        return CalendarEntryEditedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .eventDate(event.eventDate())
                .oldEventDate(event.oldEventDate())
                .eventKind(event.eventKind())
                .amount(event.amount())
                .address(event.address())
                .eventDuration(event.eventDuration())
                .description(event.description())
                .build();
    }

    static CalendarEntryRemovedNotificationEvent toNotificationEvent(final CalendarEntryRemovedEvent event) {
        return CalendarEntryRemovedNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .eventDate(event.eventDate())
                .build();
    }
}
