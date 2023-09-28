package com.qsspy.calendars.application.entries.details;

import com.qsspy.calendars.application.entries.details.port.input.GetCalendarEntryDetailsQuery;
import com.qsspy.calendars.application.entries.details.port.input.GetCalendarEntryDetailsQueryHandler;
import com.qsspy.calendars.application.entries.details.port.input.GetCalendarEntryDetailsQueryResult;
import com.qsspy.calendars.application.entries.details.port.output.CalendarEntryDetailsGetRepository;
import com.qsspy.calendars.application.entries.details.port.output.dto.CalendarEntryDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetCalendarEntryDetailsQueryHandlerImpl implements GetCalendarEntryDetailsQueryHandler {

    @Qualifier("adminCassandraCalendarEntryDetailsGetRepository")
    private final CalendarEntryDetailsGetRepository adminRepository;
    @Qualifier("memberCassandraCalendarEntryDetailsGetRepository")
    private final CalendarEntryDetailsGetRepository memberRepository;

    @Override
    public GetCalendarEntryDetailsQueryResult handle(final GetCalendarEntryDetailsQuery query) {
        query.validate();

        final CalendarEntryDetailsGetRepository repository;
        if(query.isAdmin()) {
            repository = adminRepository;
        } else {
            repository = memberRepository;
        }

        return repository.getFinanceEntryDetails(query.bandId(), query.memberId(), query.entryId())
                .map(this::mapToResult)
                .orElseThrow(() -> new IllegalStateException("Could not find calendar entry details or user has no privileges to it"));
    }

    private GetCalendarEntryDetailsQueryResult mapToResult(final CalendarEntryDetailsDTO dto) {
        return GetCalendarEntryDetailsQueryResult.builder()
                .eventDuration(dto.eventDuration())
                .address(dto.address())
                .description(dto.description())
                .build();
    }
}
