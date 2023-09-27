package com.qsspy.gateway.finances.query;

import com.qsspy.commons.rest.CustomHttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "finances-query", url = "${clients.finances-query.url}")
interface FinancesQueryConnector {

    @GetMapping("/api/v1/bands/{bandId}/finances/entries")
    GetFinanceEntriesQueryResponse addFinanceEntry(
            @RequestHeader(CustomHttpHeaders.IS_ADMIN) final boolean isAdmin,
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @PathVariable("bandId") final UUID bandId
    );
}
