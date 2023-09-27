package com.qsspy.calendars.application.membersrestrictions.port.output.dto;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CalendarEntryMemberRestrictionDTO(
        UUID entryId,
        EventKind eventKind,
        LocalDateTime eventDate,

        UUID memberId,
        String memberEmail,

        @Nullable
        Boolean canSeeCalendarEntry,
        @Nullable
        Boolean canSeeCalendarEntryPayment,
        @Nullable
        Boolean canSeeCalendarEntryDetails
) { }
