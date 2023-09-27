package com.qsspy.calendars.infrastructure.adapter.listener.domain;

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
}
