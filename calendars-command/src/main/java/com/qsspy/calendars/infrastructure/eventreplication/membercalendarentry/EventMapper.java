package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryEditedEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {
    static NotificationEvent toNotificationEvent(final CalendarEntryAddedEvent event, final DefaultCalendarEntryUserPrivilegesDTO dto) {
        return CalendarEntryAddedForMemberNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .memberId(dto.userId())
                .eventDate(event.eventDate())
                .eventKind(event.eventKind())
                .amount(event.amount())
                .address(event.address())
                .eventDuration(event.eventDuration())
                .description(event.description())
                .isVisible(dto.isVisible())
                .isVisibleDetails(dto.isVisibleDetails())
                .isVisiblePaymentAmount(dto.isVisiblePaymentAmount())
                .build();
    }

    static NotificationEvent toNotificationEvent(final CalendarEntryEditedEvent event, final UUID memberID) {
        return CalendarEntryEditedForMemberNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .memberId(memberID)
                .eventDate(event.eventDate())
                .oldEventDate(event.oldEventDate())
                .eventKind(event.eventKind())
                .amount(event.amount())
                .address(event.address())
                .eventDuration(event.eventDuration())
                .description(event.description())
                .build();
    }
}
