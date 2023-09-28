package com.qsspy.gateway.finances.command;

import com.qsspy.grpc.finances.command.AddFinanceEntryRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandRequestMapper {

    static AddFinanceEntryRequest toCommandRequest(final AddFinanceEntryRequestBody request, final UUID initiatorsBandId, final String initiatorsEmail, final UUID bandId) {
        return AddFinanceEntryRequest.newBuilder()
                .setBandId(bandId.toString())
                .setInitiatorsBandId(initiatorsBandId.toString())
                .setInitiatorsEmail(initiatorsEmail)
                .setAmount(request.amount().toString())
                .setDescription(request.description())
                .setIsOutcome(request.isOutcome())
                .build();
    }
}
