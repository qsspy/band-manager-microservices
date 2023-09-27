package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.calendars.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static CalendarEntryMemberRestrictionDTO toDto(final CalendarEntryRestrictionByBandIdUserId entity) {
        return CalendarEntryMemberRestrictionDTO.builder()
                .entryId(entity.getKey().getEventId())
                .eventKind(EventKind.valueOf(entity.getEventKind()))
                .eventDate(entity.getKey().getEventDate())
                .memberId(entity.getKey().getUserId())
                .memberEmail(entity.getMemberEmail())
                .canSeeCalendarEntry(entity.getIsVisible())
                .canSeeCalendarEntryPayment(entity.getIsVisiblePaymentAmount())
                .canSeeCalendarEntryDetails(entity.getIsVisibleDetails())
                .build();
    }
}
