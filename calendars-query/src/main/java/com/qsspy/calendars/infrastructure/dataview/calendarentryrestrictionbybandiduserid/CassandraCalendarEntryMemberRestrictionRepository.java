package com.qsspy.calendars.infrastructure.dataview.calendarentryrestrictionbybandiduserid;

import com.qsspy.calendars.application.membersrestrictions.port.output.CalendarEntryMemberRestrictionRepository;
import com.qsspy.calendars.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CassandraCalendarEntryMemberRestrictionRepository implements CalendarEntryMemberRestrictionRepository {

    private final CalendarEntryRestrictionByBandIdUserIdCassandraRepository repository;

    @Override
    public List<CalendarEntryMemberRestrictionDTO> getMemberRestrictions(final UUID bandId) {
        return repository.findByKey_BandId(bandId)
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }
}
