package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DtoMapper {

    static CalendarEntryDetailsDTO toDto(final CalendarEntryDetailsByBandIdUserId entity) {
        return CalendarEntryDetailsDTO.builder()
                .address(entity.getAddress())
                .eventDuration(entity.getHoursDuration() != null ? Duration.ofHours(entity.getHoursDuration()) : null)
                .description(entity.getDescription())
                .build();
    }
}
