package com.qsspy.authservice.infrastructure.port.adapter.listener.bandcreated;

import java.util.UUID;

record BandCreatedEvent(
        UUID eventId,
        long occurredOn,

        UUID adminId,
        UUID bandId
) {

    static final String EVENT_TYPE = "band.created";
    static final int EVENT_VERSION = 1;
}