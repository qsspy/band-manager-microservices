package com.qsspy.gateway.bands.query;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
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
class BandQueryController {

    private final AuthInterceptor authInterceptor;
    private final BandsQueryConnector connector;

    @GetMapping("/members")
    ResponseEntity<GetBandMembersQueryResponse> getBandMembers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        return ResponseEntity.ok(connector.getBandMembers(bandId));
                    } catch (final Exception exception) {
                        log.error("An error occurred during getting band users list", exception);
                        return ResponseEntity.internalServerError().build();
                    }

                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/privileges/default")
    ResponseEntity<GetBandDefaultPrivilegesQueryResponse> getBandDefaultPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        return ResponseEntity.ok(connector.getBandDefaultPrivileges(bandId));
                    } catch (final Exception exception) {
                        log.error("An error occurred during getting band default privileges", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @GetMapping("/members/{memberId}/privileges")
    ResponseEntity<GetUserBandPrivilegesQueryResponse> getBandMemberPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        return ResponseEntity.ok(connector.getBandMemberPrivileges(bandId, memberId));
                    } catch (final Exception exception) {
                        log.error("An error occurred during getting band member privileges", exception);
                        return ResponseEntity.internalServerError().build();
                    }

                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
