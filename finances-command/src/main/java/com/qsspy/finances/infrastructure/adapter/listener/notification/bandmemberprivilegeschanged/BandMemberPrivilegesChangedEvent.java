package com.qsspy.finances.infrastructure.adapter.listener.notification.bandmemberprivilegeschanged;

import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID bandId,
        UUID memberId,

        boolean canSeeFinanceIncomeEntries,
        boolean canSeeFinanceOutcomeEntries
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "band.member.privileges.changed";
    static final int EVENT_VERSION = 1;

}