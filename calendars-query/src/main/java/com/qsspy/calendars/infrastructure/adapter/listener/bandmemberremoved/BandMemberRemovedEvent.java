package com.qsspy.calendars.infrastructure.adapter.listener.bandmemberremoved;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberRemovedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        UUID bandId
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "band.member.removed";
    static final int EVENT_VERSION = 1;
}