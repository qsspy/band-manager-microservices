package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandid;

import com.qsspy.calendars.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class AdminCassandraCalendarEntryDetailsGetRepository implements CalendarEntryDetailsGetRepository {

    private final CalendarEntryDetailsByBandIdCassandraRepository repository;

    @Override
    public Optional<CalendarEntryDetailsDTO> getFinanceEntryDetails(final UUID bandId, final UUID memberId, final UUID entryId) {
        return repository
                .findById(new CalendarEntryDetailsByBandId.Id(bandId, entryId))
                .map(DtoMapper::toDto);
    }
}
