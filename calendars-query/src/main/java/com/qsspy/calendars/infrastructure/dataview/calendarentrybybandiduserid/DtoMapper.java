package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.application.entries.common.dto.EventKind;
import com.qsspy.calendars.application.entries.list.port.output.dto.CalendarEntryDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static CalendarEntryDTO toDto(final CalendarEntryByBandIdUserId entity) {
        return CalendarEntryDTO.builder()
                .id(entity.getKey().getEventId())
                .eventKind(EventKind.valueOf(entity.getEventKind()))
                .eventDate(entity.getKey().getEventDate())
                .amount(entity.getPaymentAmount())
                .canSeeDetails(entity.isVisibleDetails())
                .canSeePayment(entity.isVisiblePaymentAmount())
                .build();
    }
}
