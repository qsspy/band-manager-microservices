package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandid;

import com.qsspy.calendars.application.entries.list.port.output.CalendarEntryListRepository;
import com.qsspy.calendars.application.entries.list.port.output.dto.CalendarEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class AdminCassandraCalendarEntryListRepository implements CalendarEntryListRepository {

    private final CalendarEntryByBandIdCassandraRepository repository;

    @Override
    public List<CalendarEntryDTO> getCalendarEntries(final UUID bandId, final UUID memberId) {
        return repository.findByKey_BandId(bandId)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
