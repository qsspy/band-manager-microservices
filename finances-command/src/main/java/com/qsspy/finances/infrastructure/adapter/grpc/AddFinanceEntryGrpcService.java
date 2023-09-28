package com.qsspy.finances.infrastructure.adapter.grpc;

import com.google.protobuf.Empty;
import com.qsspy.finances.application.addition.port.input.AddFinanceEntryCommandHandler;
import com.qsspy.grpc.finances.command.AddFinanceEntryRequest;
import com.qsspy.grpc.finances.command.FinancesCommandServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
class AddFinanceEntryGrpcService extends FinancesCommandServiceGrpc.FinancesCommandServiceImplBase {

    private final AddFinanceEntryCommandHandler commandHandler;

    @Override
    public void addFinanceEntry(final AddFinanceEntryRequest request, final StreamObserver<Empty> responseObserver) {

        final var command = RequestToCommandMapper.toCommand(request);

        try {
            commandHandler.handle(command);
            responseObserver.onNext(Empty.getDefaultInstance());
        } catch (final Exception exception) {
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL));
        }

        responseObserver.onCompleted();
    }
}
