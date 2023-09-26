package com.qsspy.bands.infrastucture.adapter.listener.userregistered;

import com.qsspy.commons.port.output.publisher.DomainEvent;
import lombok.Builder;

import java.util.UUID;

record UserRegisteredEvent(
        UUID eventId,
        long occurredOn,

        UUID userId,
        String email,
        String firstName,
        String lastName

) {
    static final String EVENT_TYPE = "user.registered";
    static final int EVENT_VERSION = 1;
}
