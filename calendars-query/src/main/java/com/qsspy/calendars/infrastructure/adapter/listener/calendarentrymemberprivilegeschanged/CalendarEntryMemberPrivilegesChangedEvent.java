package com.qsspy.calendars.infrastructure.adapter.listener.calendarentrymemberprivilegeschanged;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.commons.architecture.eda.DataPropagationEvent;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryMemberPrivilegesChangedEvent(
        UUID eventId,
        long occurredOn,

        UUID entryId,
        UUID bandId,
        UUID memberId,
        LocalDateTime eventDate,

        boolean isVisible,
        boolean isVisibleDetails,
        boolean isVisiblePaymentAmount
) implements DataPropagationEvent {

    static final String EVENT_TYPE = "calendar.entry.member.privileges.changed";
    static final int EVENT_VERSION = 1;
}
