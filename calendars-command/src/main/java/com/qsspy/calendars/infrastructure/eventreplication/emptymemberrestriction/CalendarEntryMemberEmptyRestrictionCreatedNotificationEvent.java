package com.qsspy.calendars.infrastructure.eventreplication.emptymemberrestriction;

import com.qsspy.calendars.domain.calendar.EventKind;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
record CalendarEntryMemberEmptyRestrictionCreatedNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        String memberEmail,
        UUID bandId,
        UUID entryId,
        EventKind eventKind,
        LocalDateTime eventDate
) implements NotificationEvent {
    static final String EVENT_TYPE = "calendar.entry.member.empty.restriction.created";
    static final int EVENT_VERSION = 1;

    @Override
    public String eventType() {
        return EVENT_TYPE;
    }

    @Override
    public int eventVersion() {
        return EVENT_VERSION;
    }

    @Override
    public String destinationBindingName() {
        return "calendar-entry-member-empty-restriction-created";
    }

    @Override
    public Object partitionKey() {
        return bandId;
    }
}