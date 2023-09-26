package com.qsspy.gateway.finances.command;

import com.qsspy.authservice.application.authorizer.port.input.AuthInterceptor;
import com.qsspy.authservice.application.authorizer.port.input.UserContext;
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
class FinanceEntryCommandController {

    private final AuthInterceptor authInterceptor;
    private final FinancesCommandConnector connector;

    @PostMapping("/finances/entries")
    ResponseEntity<Object> addFinanceEntry(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String token,
            @PathVariable("bandId") final UUID bandId,
            @RequestBody final AddFinanceEntryRequestBody request
    ) {
        return authInterceptor.withBandMemberPrivilegeRestrictedAuthorization(
                token,
                bandId,
                UserContext.Privileges::canAddFinanceEntries,
                context -> {
                    try {
                        final var commandRequest = RequestToCommandRequestMapper.toCommandRequest(request, context.userMembershipBandId(), context.email());
                        connector.addFinanceEntry(bandId, commandRequest);
                        return ResponseEntity.ok().build();
                    } catch (final Exception exception) {
                        log.error("An error occurred while adding finance entry", exception);
                        return ResponseEntity.internalServerError().build();
                    }
                },
                () -> ResponseEntity.internalServerError().build()
        );
    }
}
