package com.qsspy.gateway.bands.command;

import com.qsspy.commons.rest.CustomHttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "bands-command", url = "${clients.bands-command.url}")
interface BandsCommandConnector {

    @PostMapping("/api/v1/bands")
    void createBand(
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @RequestBody final CreateBandRequestBody request
    );

    @PatchMapping("/api/v1/bands/{bandId}/privileges/default")
    void changeBandDefaultPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final ChangeBandPrivilegesRequestBody request
    );

    @PatchMapping("/api/v1/bands/{bandId}/members/{memberId}/privileges")
    void changeBandMemberPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId,
            @RequestBody final ChangeBandMemberPrivilegesRequestBody request
    );

    @PostMapping("/api/v1/bands/{bandId}/members")
    void addBandMember(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddBandMemberRequestBody request
    );

    @DeleteMapping("/api/v1/bands/{bandId}/members")
    void removeMemberFromBand(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final RemoveBandMemberRequestBody request
    );
}
