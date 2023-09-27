package com.qsspy.finances.infrastructure.adapter.listener.bandmemberremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId,
        String memberEmail
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "band.member.removed";
    static final int EVENT_VERSION = 1;
}