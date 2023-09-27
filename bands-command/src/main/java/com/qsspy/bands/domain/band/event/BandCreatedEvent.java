package com.qsspy.bands.domain.band.event;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandCreatedEvent(
        UUID eventId,
        long occurredOn,

        UUID adminId,
        UUID bandId
) implements DomainEvent { }