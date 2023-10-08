package com.qsspy.bands.infrastructure.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberRemovedNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId,
        String memberEmail
) implements NotificationEvent {

    static final String EVENT_TYPE = "band.member.removed";
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
        return "band-member-removed";
    }

    @Override
    public Object partitionKey() {
        return eventId;
    }
}