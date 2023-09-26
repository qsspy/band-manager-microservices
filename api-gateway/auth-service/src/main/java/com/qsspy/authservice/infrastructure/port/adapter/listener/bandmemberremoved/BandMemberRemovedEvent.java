package com.qsspy.authservice.infrastructure.port.adapter.listener.bandmemberremoved;

import com.qsspy.commons.port.output.publisher.DomainEvent;
import lombok.Builder;

import java.util.UUID;

record BandMemberRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId
) {
    static final String EVENT_TYPE = "band.member.removed";
    static final int EVENT_VERSION = 1;
}