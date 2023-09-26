package com.qsspy.calendars.application.entry.restriction;

import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommand;
import com.qsspy.calendars.domain.calendar.dto.RestrictedMemberPrivilegesData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CommandToDtoMapper {

    static RestrictedMemberPrivilegesData toDomainData(final RestrictMemberPrivilegesForEntryCommand command) {
        return RestrictedMemberPrivilegesData.builder()
                .memberId(command.memberId())
                .canSeeCalendarEntry(command.canSeeCalendarEntry())
                .canSeeCalendarEntryPayment(command.canSeeCalendarEntryPayment())
                .canSeeCalendarEntryDetails(command.canSeeCalendarEntryDetails())
                .build();
    }
}
