package com.qsspy.bands.infrastructure.adapter.listener.bandmemberadded;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberAddedEvent(
        UUID eventId,
        long occurredOn,

        UUID memberId,
        String memberEmail,
        String memberFirstName,
        String memberLastName,
        UUID bandId
) implements DataPropagationEvent {
    static final String EVENT_TYPE = "band.member.added";
    static final int EVENT_VERSION = 1;
}