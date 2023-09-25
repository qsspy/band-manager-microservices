package com.qsspy.gateway.bands.command;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands")
@RequiredArgsConstructor
@Slf4j
class BandCommandController {

    private final AuthInterceptor authInterceptor;
    private final BandsCommandConnector bandsCommandConnector;

    @PostMapping
    ResponseEntity<Object> createBand(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @RequestBody final CreateBandRequestBody request
    ) {
        return authInterceptor.withUserWithoutBandAuthorization(
                token,
                context -> {

                    try {
                        bandsCommandConnector.createBand(context.userId(), request);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred during creating band", exception);
                        return ResponseEntity.internalServerError().build();
                    }

                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @PatchMapping("/{bandId}/privileges/default")
    ResponseEntity<Object> changeBandDefaultPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final ChangeBandPrivilegesRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        bandsCommandConnector.changeBandDefaultPrivileges(bandId, request);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred during changing band default privileges", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @PatchMapping("/{bandId}/members/{memberId}/privileges")
    ResponseEntity<Object> changeBandMemberPrivileges(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @RequestBody final ChangeBandMemberPrivilegesRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        bandsCommandConnector.changeBandMemberPrivileges(bandId, memberId, request);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred during changing band member privileges", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @PostMapping("/{bandId}/members")
    ResponseEntity<Object> addBandMember(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddBandMemberRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        bandsCommandConnector.addBandMember(bandId, request);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while adding band member to band", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }

    @DeleteMapping("/{bandId}/members")
    ResponseEntity<Object> removeMemberFromBand(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final RemoveBandMemberRequestBody request
    ) {
        return authInterceptor.withBandAdminAuthorization(
                token,
                bandId,
                context -> {
                    try {
                        bandsCommandConnector.removeMemberFromBand(bandId, request);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while removing band member from band", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
