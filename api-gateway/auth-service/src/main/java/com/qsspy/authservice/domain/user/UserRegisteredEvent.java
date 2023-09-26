package com.qsspy.authservice.domain.user;

import com.qsspy.commons.architecture.eda.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRegisteredEvent(
        UUID eventId,
        long occurredOn,

        UUID userId,
        String email,
        String firstName,
        String lastName

) implements DomainEvent {

    private static final String EVENT_TYPE = "user.registered";
    private static final int EVENT_VERSION = 1;

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
        return "user-registered";
    }

    @Override
    public Object partitionKey() {
        return userId;
    }
}
