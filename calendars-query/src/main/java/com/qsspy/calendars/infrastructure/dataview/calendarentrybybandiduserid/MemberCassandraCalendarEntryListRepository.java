package com.qsspy.calendars.infrastructure.dataview.calendarentrybybandiduserid;

import com.qsspy.calendars.application.entries.list.port.output.CalendarEntryListRepository;
import com.qsspy.calendars.application.entries.list.port.output.dto.CalendarEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class MemberCassandraCalendarEntryListRepository implements CalendarEntryListRepository {

    private final CalendarEntryByBandIdUserIdCassandraRepository repository;

    @Override
    public List<CalendarEntryDTO> getCalendarEntries(final UUID bandId, final UUID memberId) {
        return repository.findByKey_BandIdAndKey_UserIdAndIsVisible(bandId, memberId, true)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
