package com.qsspy.gateway.bands.query;

import com.qsspy.commons.rest.CustomHttpHeaders;
import com.qsspy.gateway.bands.command.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "bands-query", url = "${clients.bands-query.url}")
interface BandsQueryConnector {

    @GetMapping("/api/v1/bands/{bandId}/members")
    GetBandMembersQueryResponse getBandMembers(
            @PathVariable("bandId") final UUID bandId
    );

    @GetMapping("/api/v1/bands/{bandId}/privileges/default")
    GetBandDefaultPrivilegesQueryResponse getBandDefaultPrivileges(
            @PathVariable("bandId") final UUID bandId
    );

    @GetMapping("/api/v1/bands/{bandId}/members/{memberId}/privileges")
    GetUserBandPrivilegesQueryResponse getBandMemberPrivileges(
            @PathVariable("bandId") final UUID bandId,
            @PathVariable("memberId") final UUID memberId
    );
}
