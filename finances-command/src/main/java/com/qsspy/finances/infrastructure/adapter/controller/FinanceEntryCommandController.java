package com.qsspy.finances.infrastructure.adapter.controller;

import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommandHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bands/{bandId}")
@RequiredArgsConstructor
@Slf4j
class FinanceEntryCommandController {

    private final AddFinanceEntryCommandHandler addFinanceEntryCommandHandler;

    @PostMapping("/finances/entries")
    ResponseEntity<Object> addFinanceEntry(
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddFinanceEntryRequestBody request
    ) {
        final var command = RequestToCommandMapper.toCommand(request, request.initiatorsBandId(), request.initiatorsEmail(), bandId);

        try {
            addFinanceEntryCommandHandler.handle(command);
            return ResponseEntity.ok().build();
        } catch (final Exception exception) {
            log.error("An error occurred while adding finance entry", exception);
            return ResponseEntity.internalServerError().build();
        }
    }
}
