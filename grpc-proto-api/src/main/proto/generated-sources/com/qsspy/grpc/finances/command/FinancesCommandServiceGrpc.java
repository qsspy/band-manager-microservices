package com.qsspy.grpc.finances.command;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: finances-command.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FinancesCommandServiceGrpc {

  private FinancesCommandServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "FinancesCommandService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.qsspy.grpc.finances.command.AddFinanceEntryRequest,
      com.google.protobuf.Empty> getAddFinanceEntryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddFinanceEntry",
      requestType = com.qsspy.grpc.finances.command.AddFinanceEntryRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.qsspy.grpc.finances.command.AddFinanceEntryRequest,
      com.google.protobuf.Empty> getAddFinanceEntryMethod() {
    io.grpc.MethodDescriptor<com.qsspy.grpc.finances.command.AddFinanceEntryRequest, com.google.protobuf.Empty> getAddFinanceEntryMethod;
    if ((getAddFinanceEntryMethod = FinancesCommandServiceGrpc.getAddFinanceEntryMethod) == null) {
      synchronized (FinancesCommandServiceGrpc.class) {
        if ((getAddFinanceEntryMethod = FinancesCommandServiceGrpc.getAddFinanceEntryMethod) == null) {
          FinancesCommandServiceGrpc.getAddFinanceEntryMethod = getAddFinanceEntryMethod =
              io.grpc.MethodDescriptor.<com.qsspy.grpc.finances.command.AddFinanceEntryRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddFinanceEntry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.qsspy.grpc.finances.command.AddFinanceEntryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new FinancesCommandServiceMethodDescriptorSupplier("AddFinanceEntry"))
              .build();
        }
      }
    }
    return getAddFinanceEntryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FinancesCommandServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceStub>() {
        @java.lang.Override
        public FinancesCommandServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FinancesCommandServiceStub(channel, callOptions);
        }
      };
    return FinancesCommandServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FinancesCommandServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceBlockingStub>() {
        @java.lang.Override
        public FinancesCommandServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FinancesCommandServiceBlockingStub(channel, callOptions);
        }
      };
    return FinancesCommandServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FinancesCommandServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FinancesCommandServiceFutureStub>() {
        @java.lang.Override
        public FinancesCommandServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FinancesCommandServiceFutureStub(channel, callOptions);
        }
      };
    return FinancesCommandServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    default void addFinanceEntry(com.qsspy.grpc.finances.command.AddFinanceEntryRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddFinanceEntryMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FinancesCommandService.
   */
  public static abstract class FinancesCommandServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FinancesCommandServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FinancesCommandService.
   */
  public static final class FinancesCommandServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FinancesCommandServiceStub> {
    private FinancesCommandServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinancesCommandServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FinancesCommandServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void addFinanceEntry(com.qsspy.grpc.finances.command.AddFinanceEntryRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddFinanceEntryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FinancesCommandService.
   */
  public static final class FinancesCommandServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FinancesCommandServiceBlockingStub> {
    private FinancesCommandServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinancesCommandServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FinancesCommandServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.protobuf.Empty addFinanceEntry(com.qsspy.grpc.finances.command.AddFinanceEntryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddFinanceEntryMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FinancesCommandService.
   */
  public static final class FinancesCommandServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FinancesCommandServiceFutureStub> {
    private FinancesCommandServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FinancesCommandServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FinancesCommandServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addFinanceEntry(
        com.qsspy.grpc.finances.command.AddFinanceEntryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddFinanceEntryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_FINANCE_ENTRY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_FINANCE_ENTRY:
          serviceImpl.addFinanceEntry((com.qsspy.grpc.finances.command.AddFinanceEntryRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAddFinanceEntryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.qsspy.grpc.finances.command.AddFinanceEntryRequest,
              com.google.protobuf.Empty>(
                service, METHODID_ADD_FINANCE_ENTRY)))
        .build();
  }

  private static abstract class FinancesCommandServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FinancesCommandServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.qsspy.grpc.finances.command.FinancesCommand.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FinancesCommandService");
    }
  }

  private static final class FinancesCommandServiceFileDescriptorSupplier
      extends FinancesCommandServiceBaseDescriptorSupplier {
    FinancesCommandServiceFileDescriptorSupplier() {}
  }

  private static final class FinancesCommandServiceMethodDescriptorSupplier
      extends FinancesCommandServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FinancesCommandServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FinancesCommandServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FinancesCommandServiceFileDescriptorSupplier())
              .addMethod(getAddFinanceEntryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
