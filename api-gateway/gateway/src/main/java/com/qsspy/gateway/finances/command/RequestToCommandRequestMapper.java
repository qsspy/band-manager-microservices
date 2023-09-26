package com.qsspy.gateway.finances.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandRequestMapper {

    static AddFinanceEntryCommandRequestBody toCommandRequest(final AddFinanceEntryRequestBody request, final UUID initiatorsBandId, final String initiatorsEmail) {
        return AddFinanceEntryCommandRequestBody.builder()
                .initiatorsBandId(initiatorsBandId)
                .initiatorsEmail(initiatorsEmail)

                .amount(request.amount())
                .description(request.description())
                .isOutcome(request.isOutcome())
                .build();
    }
}
