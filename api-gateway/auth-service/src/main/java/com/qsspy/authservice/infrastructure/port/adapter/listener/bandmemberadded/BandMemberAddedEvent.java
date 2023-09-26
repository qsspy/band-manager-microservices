package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberadded;

import lombok.Builder;

import java.util.UUID;

@Builder
record BandMemberAddedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId
) {
    static final String EVENT_TYPE = "band.member.added";
    static final int EVENT_VERSION = 1;
}