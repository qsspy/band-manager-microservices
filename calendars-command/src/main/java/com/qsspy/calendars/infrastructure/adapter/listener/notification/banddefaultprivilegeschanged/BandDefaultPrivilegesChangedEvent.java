package com.qsspy.calendars.infrastructure.adapter.listener.notification.banddefaultprivilegeschanged;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import com.qsspy.commons.architecture.eda.NotificationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandDefaultPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        boolean canSeeCalendarEntryByDefault,
        boolean canSeeCalendarEntryPaymentByDefault,
        boolean canSeeCalendarEntryDetailsByDefault
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "band.default.privileges.changed";
    static final int EVENT_VERSION = 1;
}