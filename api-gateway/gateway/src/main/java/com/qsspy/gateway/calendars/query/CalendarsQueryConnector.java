package com.qsspy.gateway.calendars.query;

import com.qsspy.commons.rest.CustomHttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "calendars-query", url = "${clients.calendars-query.url}")
interface CalendarsQueryConnector {

    @GetMapping("/api/v1/bands/{bandId}/calendar/entries/member-privileges")
    GetCalendarEntryMemberRestrictionQueryResponse getCalenderEntryMemberRestrictions(
            @PathVariable("bandId") final UUID bandId
    );

    @GetMapping("/api/v1/bands/{bandId}/calendar/entries/{entryId}")
    GetCalendarEntryDetailsQueryResponse getCalenderEntryDetails(
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @RequestHeader(CustomHttpHeaders.IS_ADMIN) final boolean isAdmin,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    );

    @GetMapping("/api/v1/bands/{bandId}/calendar/entries")
    GetCalendarEntryListQueryResponse getCalenderEntries(
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @RequestHeader(CustomHttpHeaders.IS_ADMIN) final boolean isAdmin,
            @PathVariable("bandId") final UUID bandId
    );
}
