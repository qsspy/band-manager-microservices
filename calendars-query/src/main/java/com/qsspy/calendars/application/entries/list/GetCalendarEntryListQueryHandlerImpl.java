package com.qsspy.calendars.application.entries.list;

import com.qsspy.calendars.application.entries.list.port.input.GetCalendarEntryListQuery;
import com.qsspy.calendars.application.entries.list.port.input.GetCalendarEntryListQueryHandler;
import com.qsspy.calendars.application.entries.list.port.input.GetCalendarEntryListQueryResult;
import com.qsspy.calendars.application.entries.list.port.output.CalendarEntryListRepository;
import com.qsspy.calendars.application.entries.list.port.output.dto.CalendarEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class GetCalendarEntryListQueryHandlerImpl implements GetCalendarEntryListQueryHandler {

    @Qualifier("memberCassandraCalendarEntryListRepository")
    private final CalendarEntryListRepository memberRepository;
    @Qualifier("adminCassandraCalendarEntryListRepository")
    private final CalendarEntryListRepository adminRepository;

    @Override
    public GetCalendarEntryListQueryResult handle(final GetCalendarEntryListQuery query) {
        query.validate();

        final CalendarEntryListRepository repository;
        if(query.isAdmin()) {
            repository = adminRepository;
        } else {
            repository = memberRepository;
        }

        final var responseItems = repository.getCalendarEntries(query.bandId(), query.memberId()).stream()
                .map(this::toResponseItem)
                .toList();

        return new GetCalendarEntryListQueryResult(responseItems);
    }

    private GetCalendarEntryListQueryResult.ListItem toResponseItem(final CalendarEntryDTO entryDTO) {
        return GetCalendarEntryListQueryResult.ListItem.builder()
                .id(entryDTO.id())
                .eventKind(entryDTO.eventKind())
                .eventDate(entryDTO.eventDate())
                .amount(resolveAmountByPrivileges(entryDTO))
                .canSeeDetails(resolveCanSeeDetailsPrivilege(entryDTO))
                .build();
    }

    @Nullable
    private BigDecimal resolveAmountByPrivileges(final CalendarEntryDTO entryDTO) {
        if(entryDTO.canSeePayment() == null) {
            return entryDTO.canSeePaymentByDefault() ? entryDTO.amount() : null;
        }
        return entryDTO.canSeePayment() ? entryDTO.amount() : null;
    }

    private boolean resolveCanSeeDetailsPrivilege(final CalendarEntryDTO entryDTO) {
        if(entryDTO.canSeeDetails() == null) {
            return entryDTO.canSeeDetailsByDefault();
        }
        return entryDTO.canSeeDetails();
    }
}
