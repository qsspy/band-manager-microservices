package com.qsspy.bands.infrastucture.adapter.listener.userregistered;

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
