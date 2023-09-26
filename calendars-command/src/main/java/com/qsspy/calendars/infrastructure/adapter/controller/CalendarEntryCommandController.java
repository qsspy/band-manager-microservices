package com.qsspy.calendars.infrastructure.adapter.controller;

import com.qsspy.calendars.application.entry.create.port.input.CreateCalendarEntryCommandHandler;
import com.qsspy.calendars.application.entry.edit.port.input.EditCalendarEntryCommandHandler;
import com.qsspy.calendars.application.entry.remove.port.input.RemoveCalendarEntryCommandHandler;
import com.qsspy.calendars.application.entry.restriction.port.input.RestrictMemberPrivilegesForEntryCommandHandler;
import com.qsspy.commons.rest.CustomHttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands/{bandId}")
@RequiredArgsConstructor
@Slf4j
class CalendarEntryCommandController {

    private final CreateCalendarEntryCommandHandler createCalendarEntryCommandHandler;
    private final RemoveCalendarEntryCommandHandler removeCalendarEntryCommandHandler;
    private final EditCalendarEntryCommandHandler editCalendarEntryCommandHandler;
    private final RestrictMemberPrivilegesForEntryCommandHandler restrictMemberPrivilegesForEntryCommandHandler;

    @PostMapping("/calendar/entries")
    ResponseEntity<Object> addCalendarEntry(
            @PathVariable("bandId") final UUID bandId,
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @RequestBody final CalendarEntryRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(request, initiatorsBandId, bandId);

        try {
            createCalendarEntryCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while adding calendar entry", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/calendar/entries/{entryId}")
    ResponseEntity<Object> removeCalendarEntry(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    ) {
        final var command = RequestToCommandMapper.toCommand(initiatorsBandId, bandId, entryId);

        try {
            removeCalendarEntryCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while removing calendar entry", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/calendar/entries/{entryId}")
    ResponseEntity<Object> editCalendarEntry(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final CalendarEntryRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(request, initiatorsBandId, bandId, entryId);

        try {
            editCalendarEntryCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while editing calendar entry", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/members/{memberId}/calendar/entries/{entryId}/privileges")
    ResponseEntity<Object> editCalendarEntryRestrictedMemberPrivileges(
            @RequestHeader(CustomHttpHeaders.INITIATOR_BAND_ID) final UUID initiatorsBandId,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @PathVariable("entryId") final UUID entryId,
            @RequestBody final RestrictMemberPrivilegesForEntryRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(request, initiatorsBandId, bandId, entryId, memberId);

        try {
            restrictMemberPrivilegesForEntryCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while editing calendar entry privileges for member", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
