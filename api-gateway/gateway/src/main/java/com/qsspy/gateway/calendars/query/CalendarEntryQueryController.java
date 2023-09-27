package com.qsspy.gateway.calendars.query;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
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
class CalendarEntryQueryController {

    private final AuthInterceptor authInterceptor;
    private final CalendarsQueryConnector connector;

    @GetMapping("/calendar/entries/member-privileges")
    ResponseEntity<GetCalendarEntryMemberRestrictionQueryResponse> getCalenderEntryMemberRestrictions(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        final var response = connector.getCalenderEntryMemberRestrictions(bandId);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entry member restrictions", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/calendar/entries/{entryId}")
    ResponseEntity<GetCalendarEntryDetailsQueryResponse> getCalenderEntryDetails(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("entryId") final UUID entryId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAccessCalendar,
                context -> {

                    try {
                        final var response = connector.getCalenderEntryDetails(context.userId(), context.userOwnBandId() != null, bandId, entryId);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entry details", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/calendar/entries")
    ResponseEntity<GetCalendarEntryListQueryResponse> getCalenderEntries(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAccessCalendar,
                context -> {
                    try {
                        final var response = connector.getCalenderEntries(context.userId(), context.userOwnBandId() != null, bandId);
                        return ResponseEntity.ok(response);
                    } catch (final Exception exception) {
                        log.error("An error occurred while fetching calendar entries", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
