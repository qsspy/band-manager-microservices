package com.qsspy.calendars.infrastructure.port.repository;

import com.qsspy.calendars.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import com.qsspy.calendars.application.entries.list.port.output.CalendarEntryListRepository;
import com.qsspy.calendars.application.entries.list.port.output.dto.CalendarEntryDTO;
import com.qsspy.calendars.application.membersrestrictions.port.output.CalendarEntryMemberRestrictionRepository;
import com.qsspy.calendars.application.membersrestrictions.port.output.dto.CalendarEntryMemberRestrictionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class DatabaseCalendarRepository implements CalendarEntryMemberRestrictionRepository, CalendarEntryDetailsGetRepository, CalendarEntryListRepository {

    @Override
    public Optional<CalendarEntryDetailsDTO> getFinanceEntryDetails(UUID bandId, UUID memberId, UUID entryId) {
        return Optional.empty();
    }

    @Override
    public List<CalendarEntryDTO> getCalendarEntries(UUID bandId, UUID memberId) {
        return null;
    }

    @Override
    public List<CalendarEntryMemberRestrictionDTO> getMemberRestrictions(UUID bandId) {
        return null;
    }
}
