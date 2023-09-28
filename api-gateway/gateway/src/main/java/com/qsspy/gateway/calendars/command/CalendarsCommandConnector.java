package com.qsspy.gateway.calendars.command;

import com.qsspy.commons.rest.CustomHttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "calendars-command", url = "${clients.calendars-command.url}")
interface CalendarsCommandConnector {

    @PostMapping("/api/v1/bands/{bandId}/calendar/entries")
    void addCalendarEntry(
            @PathVariable("bandId") final UUID bandId,
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @RequestBody final CalendarEntryRequestBody request
    );

    @DeleteMapping("/api/v1/bands/{bandId}/calendar/entries/{entryId}")
    void removeCalendarEntry(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    );

    @PutMapping("/api/v1/bands/{bandId}/calendar/entries/{entryId}")
    void editCalendarEntry(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final CalendarEntryRequestBody request
    );

    @PutMapping("/api/v1/bands/{bandId}/members/{memberId}/calendar/entries/{entryId}/privileges")
    void editCalendarEntryRestrictedMemberPrivileges(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final RestrictMemberPrivilegesForEntryRequestBody request
    );
}
