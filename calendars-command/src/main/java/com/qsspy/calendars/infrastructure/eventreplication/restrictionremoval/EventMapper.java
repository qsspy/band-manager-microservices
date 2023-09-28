package com.qsspy.calendars.infrastructure.eventreplication.restrictionremoval;

import com.qsspy.calendars.infrastructure.adapter.listener.notification.bandmemberremoved.BandMemberRemovedEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class EventMapper {

    static NotificationEvent toNotificationEvent(final BandMemberRemovedEvent event, final CalendarEntryIdDTO dto) {
        return CalendarEntryMemberRestrictionRemovedNotificationEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredOn(Instant.now().toEpochMilli())

                .memberId(event.memberId())
                .bandId(event.bandId())
                .entryId(dto.entryId())
                .eventDate(dto.eventDate())
                .build();
    }
}
