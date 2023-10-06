package com.qsspy.bands.infrastructure.adapter.controller;

import com.qsspy.bands.application.creation.port.input.CreateBandCommand;
import com.qsspy.bands.application.creation.port.input.CreateBandCommandHandler;
import com.qsspy.bands.application.defaultprivileges.port.input.ChangeBandDefaultPrivilegesCommandHandler;
import com.qsspy.bands.application.member.addition.port.input.AddBandMemberCommand;
import com.qsspy.bands.application.member.addition.port.input.AddBandMemberCommandHandler;
import com.qsspy.bands.application.member.changeprivileges.port.input.ChangeMemberPrivilegesCommandHandler;
import com.qsspy.bands.application.member.removal.port.input.RemoveBandMemberCommand;
import com.qsspy.bands.application.member.removal.port.input.RemoveBandMemberCommandHandler;
import com.qsspy.commons.rest.CustomHttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands")
@RequiredArgsConstructor
@Slf4j
class BandCommandController {

    private final CreateBandCommandHandler createBandCommandHandler;
    private final ChangeBandDefaultPrivilegesCommandHandler changeBandDefaultPrivilegesCommandHandler;
    private final ChangeMemberPrivilegesCommandHandler changeMemberPrivilegesCommandHandler;
    private final AddBandMemberCommandHandler addBandMemberCommandHandler;
    private final RemoveBandMemberCommandHandler removeBandMemberCommandHandler;

    @PostMapping
    ResponseEntity<Object> createBand(
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @RequestBody final CreateBandRequestBody request
    ) {
        final var command = CreateBandCommand.builder()
                .creatorId(userId)
                .bandName(request.name())
                .build();

        try {
            createBandCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred during creating band", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{bandId}/privileges/default")
    ResponseEntity<Object> changeBandDefaultPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final ChangeBandPrivilegesRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(bandId, request);
        try {
            changeBandDefaultPrivilegesCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred during changing band default privileges", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{bandId}/members/{memberId}/privileges")
    ResponseEntity<Object> changeBandMemberPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @RequestBody final ChangeBandMemberPrivilegesRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(bandId, memberId, request);
        try {
            changeMemberPrivilegesCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred during changing band member privileges", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{bandId}/members")
    ResponseEntity<Object> addBandMember(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddBandMemberRequestBody request
    ) {
        final var command = new AddBandMemberCommand(request.userEmail(), bandId);

        try {
            addBandMemberCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while adding band member to band", exception);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{bandId}/members")
    ResponseEntity<Object> removeMemberFromBand(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final RemoveBandMemberRequestBody request
    ) {
        final var command = new RemoveBandMemberCommand(bandId, request.userId());

        try {
            removeBandMemberCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while removing band member from band", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
