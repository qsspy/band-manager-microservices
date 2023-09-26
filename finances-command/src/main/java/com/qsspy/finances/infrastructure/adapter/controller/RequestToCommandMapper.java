package com.qsspy.finances.infrastructure.adapter.controller;

import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static AddFinanceEntryCommand toCommand(
            final AddFinanceEntryRequestBody request,
            final UUID initiatorsBandId,
            final String initiatorsEmail,
            final UUID bandId
            ) {
        return AddFinanceEntryCommand.builder()
                .initiatorsBandId(initiatorsBandId)
                .bandId(bandId)
                .amount(request.amount())
                .description(request.description())
                .initiatorEmail(initiatorsEmail)
                .isOutcome(request.isOutcome())
                .build();
    }
}
