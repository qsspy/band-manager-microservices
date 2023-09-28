package com.qsspy.calendars.infrastructure.eventreplication.membercalendarentry;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryEditedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRemovedEvent;
import com.qsspy.calendars.domain.calendar.event.CalendarEntryRestrictionForMemberChangedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.notification.banddefaultprivilegeschanged.BandDefaultPrivilegesChangedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
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

    static NotificationEvent toNotificationEvent(final CalendarEntryEditedEvent event, final UUID memberId) {
        return CalendarEntryEditedForMemberNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .memberId(memberId)
                .eventDate(event.eventDate())
                .oldEventDate(event.oldEventDate())
                .eventKind(event.eventKind())
                .amount(event.amount())
                .address(event.address())
                .eventDuration(event.eventDuration())
                .description(event.description())
                .build();
    }

    static NotificationEvent toNotificationEvent(final CalendarEntryRemovedEvent event, final UUID memberId) {
        return CalendarEntryRemovedForMemberNotificationEvent.builder()
                .eventId(event.eventId())
                .occurredOn(event.occurredOn())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .memberId(memberId)
                .eventDate(event.eventDate())
                .build();
    }

    static NotificationEvent toNotificationEvent(final BandDefaultPrivilegesChangedEvent event, final EntryIdWithMemberIdDTO dto) {
        return CalendarEntryMemberPrivilegesChangedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .entryId(dto.entryId())
                .bandId(event.bandId())
                .memberId(dto.memberId())
                .eventDate(dto.eventDate())

                .isVisible(event.canSeeCalendarEntryByDefault())
                .isVisibleDetails(event.canSeeCalendarEntryDetailsByDefault())
                .isVisiblePaymentAmount(event.canSeeCalendarEntryPaymentByDefault())
                .build();
    }

    static NotificationEvent toNotificationEvent(final CalendarEntryRestrictionForMemberChangedEvent event) {
        return CalendarEntryMemberPrivilegesChangedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .entryId(event.entryId())
                .bandId(event.bandId())
                .memberId(event.memberId())
                .eventDate(event.eventDate())

                .isVisible(event.isVisible())
                .isVisibleDetails(event.isVisibleDetails())
                .isVisiblePaymentAmount(event.isVisiblePaymentAmount())
                .build();
    }

    static NotificationEvent toNotificationEvent(final CalendarEntryWithPrivilegesDTO dto, final UUID memberId) {
        return CalendarEntryAddedForMemberNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .entryId(dto.entryId())
                .bandId(dto.bandId())
                .memberId(memberId)
                .eventDate(dto.eventDate())
                .eventKind(dto.eventKind())
                .amount(dto.amount())
                .address(dto.address())
                .eventDuration(dto.eventDuration())
                .description(dto.description())
                .isVisible(dto.isVisible())
                .isVisibleDetails(dto.isVisibleDetails())
                .isVisiblePaymentAmount(dto.isVisiblePaymentAmount())
                .build();
    }

    static NotificationEvent toNotificationEvent(final BandMemberRemovedEvent event, final CalendarEntryIdDTO dto) {
        return CalendarEntryRemovedForMemberNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .memberId(event.memberId())
                .bandId(event.bandId())
                .entryId(dto.entryId())
                .eventDate(dto.eventDate())
                .build();
    }
}
