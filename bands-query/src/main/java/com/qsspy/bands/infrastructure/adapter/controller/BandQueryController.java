package com.qsspy.bands.infrastructure.adapter.controller;

import com.qsspy.bands.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQuery;
import com.qsspy.bands.application.defaultprivileges.port.input.GetBandDefaultPrivilegesQueryHandler;
import com.qsspy.bands.application.members.port.input.GetBandMembersQuery;
import com.qsspy.bands.application.members.port.input.GetBandMembersQueryHandler;
import com.qsspy.bands.application.userprivileges.port.input.GetUserBandPrivilegesQuery;
import com.qsspy.bands.application.userprivileges.port.input.GetUserBandPrivilegesQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands/{bandId}")
@RequiredArgsConstructor
@Slf4j
class BandQueryController {

    private final GetBandMembersQueryHandler getBandMembersQueryHandler;
    private final GetBandDefaultPrivilegesQueryHandler getBandDefaultPrivilegesQueryHandler;
    private final GetUserBandPrivilegesQueryHandler getUserBandPrivilegesQueryHandler;

    @GetMapping("/members")
    ResponseEntity<GetBandMembersQueryResponse> getBandMembers(
            @PathVariable("bandId") final UUID bandId
    ) {
        final var query = new GetBandMembersQuery(bandId);

        try {
            final var result = getBandMembersQueryHandler.handle(query);
            final var response = QueryResultToResponseMapper.toResponse(result);
            return ResponseEntity.ok(response);
        } catch (final Exception exception) {
            log.error("An error occurred during getting band users list", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/privileges/default")
    ResponseEntity<GetBandDefaultPrivilegesQueryResponse> getBandDefaultPrivileges(
            @PathVariable("bandId") final UUID bandId
    ) {
        final var query = new GetBandDefaultPrivilegesQuery(bandId);

        try {
            final var result = getBandDefaultPrivilegesQueryHandler.handle(query);
            final var response = QueryResultToResponseMapper.toResponse(result);
            return ResponseEntity.ok(response);
        } catch (final Exception exception) {
            log.error("An error occurred during getting band default privileges", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/members/{memberId}/privileges")
    ResponseEntity<GetUserBandPrivilegesQueryResponse> getBandDefaultPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId
    ) {
        final var query = new GetUserBandPrivilegesQuery(bandId, memberId);

        try {
            final var result = getUserBandPrivilegesQueryHandler.handle(query);
            final var response = QueryResultToResponseMapper.toResponse(result);
            return ResponseEntity.ok(response);
        } catch (final Exception exception) {
            log.error("An error occurred during getting band member privileges", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
