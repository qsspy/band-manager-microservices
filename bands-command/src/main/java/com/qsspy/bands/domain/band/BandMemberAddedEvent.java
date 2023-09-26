package com.qsspy.bands.domain.band;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberAddedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        String memberEmail,
        String memberFirstName,
        String memberLastName,
        UUID bandId
) implements DomainEvent {

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