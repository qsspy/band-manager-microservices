package com.qsspy.bands.domain.band.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
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
) implements DomainEvent { }