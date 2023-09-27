package com.qsspy.bands.infrastucture.adapter.listener.domain;

import com.qsspy.commons.architecture.eda.DomainEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberAddedNotificationEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        String memberEmail,
        String memberFirstName,
        String memberLastName,
        UUID bandId
) implements NotificationEvent {

    static final String EVENT_TYPE = "band.member.added";
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
        return "band-member-added";
    }

    @Override
    public Object partitionKey() {
        return memberId;
    }
}