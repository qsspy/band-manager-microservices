package com.qsspy.finances.infrastructure.adapter.controller;

import com.qsspy.commons.rest.CustomHttpHeaders;
import com.qsspy.finances.application.entries.port.input.GetFinanceEntriesQuery;
import com.qsspy.finances.application.entries.port.input.GetFinanceEntriesQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands/{bandId}")
@RequiredArgsConstructor
@Slf4j
class FinanceEntryQueryController {

    private final GetFinanceEntriesQueryHandler getFinanceEntriesQueryHandler;

    @GetMapping("/finances/entries")
    ResponseEntity<Object> addFinanceEntry(
            @RequestHeader(CustomHttpHeaders.USER_ID) final UUID userId,
            @RequestHeader(CustomHttpHeaders.IS_ADMIN) final boolean isAdmin,
            @PathVariable("bandId") final UUID bandId
    ) {
        final var query = new GetFinanceEntriesQuery(bandId, userId, isAdmin);

        try {
            final var result = getFinanceEntriesQueryHandler.handle(query);
            final var response = QueryResultToResponseMapper.toResponse(result);
            return ResponseEntity.ok(response);
        } catch (final Exception exception) {
            log.error("An error occurred while fetching finance entries", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
