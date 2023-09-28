package com.qsspy.calendars.infrastructure.dataview.calendarentrydetailsbybandiduserid;

import com.qsspy.calendars.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class MemberCassandraCalendarEntryDetailsGetRepository implements CalendarEntryDetailsGetRepository {

    private final CalendarEntryDetailsByBandIdUserIdCassandraRepository repository;

    @Override
    public Optional<CalendarEntryDetailsDTO> getFinanceEntryDetails(final UUID bandId, final UUID memberId, final UUID entryId) {
        return repository.findByKey_BandIdAndKey_UserIdAndKey_EventIdAndIsVisible(
                bandId,
                memberId,
                entryId,
                true
        )
                .map(DtoMapper::toDto);
    }
}
