package com.qsspy.gateway.finances.command;

import com.qsspy.commons.rest.CustomHttpHeaders;
import com.qsspy.gateway.bands.command.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "finances-command", url = "${clients.finances-command.url}")
interface FinancesCommandConnector {

    @PostMapping("/api/v1/bands/{bandId}/finances/entries")
    void addFinanceEntry(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddFinanceEntryCommandRequestBody request
    );
}
