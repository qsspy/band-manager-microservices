package com.qsspy.bands.infrastructure.adapter.listener.bandmemberremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;

import java.util.UUID;

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