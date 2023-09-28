package com.qsspy.gateway.finances.command;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "finances-command", url = "${clients.finances-command.url}")
interface FinancesCommandConnector {

    @PostMapping("/api/v1/bands/{bandId}/finances/entries")
    void addFinanceEntry(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddFinanceEntryCommandRequestBody request
    );
}
