package com.qsspy.finances.infrastructure.adapter.grpc;

import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommand;
import com.qsspy.grpc.finances.command.AddFinanceEntryRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class RequestToCommandMapper {

    static AddFinanceEntryCommand toCommand(final AddFinanceEntryRequest request) {
        return AddFinanceEntryCommand.builder()
                .initiatorsBandId(UUID.fromString(request.getInitiatorsBandId()))
                .bandId(UUID.fromString(request.getBandId()))
                .amount(new BigDecimal(request.getAmount()))
                .description(request.getDescription())
                .initiatorEmail(request.getInitiatorsEmail())
                .isOutcome(request.getIsOutcome())
                .build();
    }
}
