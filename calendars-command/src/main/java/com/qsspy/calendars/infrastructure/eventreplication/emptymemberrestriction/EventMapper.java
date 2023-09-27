package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.domain.calendar.event.CalendarEntryAddedEvent;
import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberadded.BandMemberAddedEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {

    static NotificationEvent toNotificationEvent(final CalendarEntryPrivilegesView entry, final BandMemberAddedEvent event) {
        return CalendarEntryMemberEmptyRestrictionCreatedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .memberId(event.memberId())
                .memberEmail(event.memberEmail())
                .bandId(event.bandId())
                .entryId(entry.id())
                .eventKind(entry.eventKind())
                .eventDate(entry.eventDate())
                .build();
    }

    static NotificationEvent toNotificationEvent(final BandMemberEntity memberEntity, final CalendarEntryAddedEvent event) {
        return CalendarEntryMemberEmptyRestrictionCreatedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .memberId(memberEntity.getId().getMemberId())
                .memberEmail(memberEntity.getEmail())
                .bandId(event.bandId())
                .entryId(event.entryId())
                .eventKind(event.eventKind())
                .eventDate(event.eventDate())
                .build();
    }
}
