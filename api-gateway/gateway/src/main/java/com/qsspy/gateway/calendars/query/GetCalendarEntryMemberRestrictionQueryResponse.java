package com.qsspy.gateway.calendars.query;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

record GetCalendarEntryMemberRestrictionQueryResponse(
        Map<UUID, EntryRestriction> entryRestrictions
) {

    @Builder
    public record EntryRestriction(
            String eventKind,
            LocalDateTime eventDate,
            Map<UUID, Member> memberPrivileges
    ) {

        @Builder
        public record Member(
                String memberEmail,
                @Nullable
                Boolean canSeeCalendarEntry,
                @Nullable
                Boolean canSeeCalendarEntryPayment,
                @Nullable
                Boolean canSeeCalendarEntryDetails
        ) { }
    }
}
