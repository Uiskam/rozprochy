package io.grpc.examples.calculator;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: calculator/calculator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CalculatorGrpc {

  private CalculatorGrpc() {}

  public static final java.lang.String SERVICE_NAME = "calculator.Calculator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments,
      io.grpc.examples.calculator.ArithmeticOpResult> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Add",
      requestType = io.grpc.examples.calculator.ArithmeticOpArguments.class,
      responseType = io.grpc.examples.calculator.ArithmeticOpResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments,
      io.grpc.examples.calculator.ArithmeticOpResult> getAddMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments, io.grpc.examples.calculator.ArithmeticOpResult> getAddMethod;
    if ((getAddMethod = CalculatorGrpc.getAddMethod) == null) {
      synchronized (CalculatorGrpc.class) {
        if ((getAddMethod = CalculatorGrpc.getAddMethod) == null) {
          CalculatorGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.calculator.ArithmeticOpArguments, io.grpc.examples.calculator.ArithmeticOpResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.ArithmeticOpArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.ArithmeticOpResult.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorMethodDescriptorSupplier("Add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments,
      io.grpc.examples.calculator.ArithmeticOpResult> getSubtractMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subtract",
      requestType = io.grpc.examples.calculator.ArithmeticOpArguments.class,
      responseType = io.grpc.examples.calculator.ArithmeticOpResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments,
      io.grpc.examples.calculator.ArithmeticOpResult> getSubtractMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.calculator.ArithmeticOpArguments, io.grpc.examples.calculator.ArithmeticOpResult> getSubtractMethod;
    if ((getSubtractMethod = CalculatorGrpc.getSubtractMethod) == null) {
      synchronized (CalculatorGrpc.class) {
        if ((getSubtractMethod = CalculatorGrpc.getSubtractMethod) == null) {
          CalculatorGrpc.getSubtractMethod = getSubtractMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.calculator.ArithmeticOpArguments, io.grpc.examples.calculator.ArithmeticOpResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subtract"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.ArithmeticOpArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.ArithmeticOpResult.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorMethodDescriptorSupplier("Subtract"))
              .build();
        }
      }
    }
    return getSubtractMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.examples.calculator.MultipleArithmeticOperations,
      io.grpc.examples.calculator.ArithmeticOpResult> getManyArithmeticOperationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ManyArithmeticOperations",
      requestType = io.grpc.examples.calculator.MultipleArithmeticOperations.class,
      responseType = io.grpc.examples.calculator.ArithmeticOpResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.examples.calculator.MultipleArithmeticOperations,
      io.grpc.examples.calculator.ArithmeticOpResult> getManyArithmeticOperationsMethod() {
    io.grpc.MethodDescriptor<io.grpc.examples.calculator.MultipleArithmeticOperations, io.grpc.examples.calculator.ArithmeticOpResult> getManyArithmeticOperationsMethod;
    if ((getManyArithmeticOperationsMethod = CalculatorGrpc.getManyArithmeticOperationsMethod) == null) {
      synchronized (CalculatorGrpc.class) {
        if ((getManyArithmeticOperationsMethod = CalculatorGrpc.getManyArithmeticOperationsMethod) == null) {
          CalculatorGrpc.getManyArithmeticOperationsMethod = getManyArithmeticOperationsMethod =
              io.grpc.MethodDescriptor.<io.grpc.examples.calculator.MultipleArithmeticOperations, io.grpc.examples.calculator.ArithmeticOpResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ManyArithmeticOperations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.MultipleArithmeticOperations.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.examples.calculator.ArithmeticOpResult.getDefaultInstance()))
              .setSchemaDescriptor(new CalculatorMethodDescriptorSupplier("ManyArithmeticOperations"))
              .build();
        }
      }
    }
    return getManyArithmeticOperationsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculatorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorStub>() {
        @java.lang.Override
        public CalculatorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorStub(channel, callOptions);
        }
      };
    return CalculatorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorBlockingStub>() {
        @java.lang.Override
        public CalculatorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorBlockingStub(channel, callOptions);
        }
      };
    return CalculatorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculatorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculatorFutureStub>() {
        @java.lang.Override
        public CalculatorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculatorFutureStub(channel, callOptions);
        }
      };
    return CalculatorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void add(io.grpc.examples.calculator.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    default void subtract(io.grpc.examples.calculator.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubtractMethod(), responseObserver);
    }

    /**
     */
    default void manyArithmeticOperations(io.grpc.examples.calculator.MultipleArithmeticOperations request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getManyArithmeticOperationsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Calculator.
   */
  public static abstract class CalculatorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CalculatorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Calculator.
   */
  public static final class CalculatorStub
      extends io.grpc.stub.AbstractAsyncStub<CalculatorStub> {
    private CalculatorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorStub(channel, callOptions);
    }

    /**
     */
    public void add(io.grpc.examples.calculator.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subtract(io.grpc.examples.calculator.ArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubtractMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void manyArithmeticOperations(io.grpc.examples.calculator.MultipleArithmeticOperations request,
        io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getManyArithmeticOperationsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Calculator.
   */
  public static final class CalculatorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CalculatorBlockingStub> {
    private CalculatorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.examples.calculator.ArithmeticOpResult add(io.grpc.examples.calculator.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.examples.calculator.ArithmeticOpResult subtract(io.grpc.examples.calculator.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubtractMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.grpc.examples.calculator.ArithmeticOpResult manyArithmeticOperations(io.grpc.examples.calculator.MultipleArithmeticOperations request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getManyArithmeticOperationsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Calculator.
   */
  public static final class CalculatorFutureStub
      extends io.grpc.stub.AbstractFutureStub<CalculatorFutureStub> {
    private CalculatorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculatorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculatorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.calculator.ArithmeticOpResult> add(
        io.grpc.examples.calculator.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.calculator.ArithmeticOpResult> subtract(
        io.grpc.examples.calculator.ArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubtractMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.examples.calculator.ArithmeticOpResult> manyArithmeticOperations(
        io.grpc.examples.calculator.MultipleArithmeticOperations request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getManyArithmeticOperationsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD = 0;
  private static final int METHODID_SUBTRACT = 1;
  private static final int METHODID_MANY_ARITHMETIC_OPERATIONS = 2;

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
        case METHODID_ADD:
          serviceImpl.add((io.grpc.examples.calculator.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_SUBTRACT:
          serviceImpl.subtract((io.grpc.examples.calculator.ArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult>) responseObserver);
          break;
        case METHODID_MANY_ARITHMETIC_OPERATIONS:
          serviceImpl.manyArithmeticOperations((io.grpc.examples.calculator.MultipleArithmeticOperations) request,
              (io.grpc.stub.StreamObserver<io.grpc.examples.calculator.ArithmeticOpResult>) responseObserver);
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
          getAddMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.calculator.ArithmeticOpArguments,
              io.grpc.examples.calculator.ArithmeticOpResult>(
                service, METHODID_ADD)))
        .addMethod(
          getSubtractMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.calculator.ArithmeticOpArguments,
              io.grpc.examples.calculator.ArithmeticOpResult>(
                service, METHODID_SUBTRACT)))
        .addMethod(
          getManyArithmeticOperationsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.grpc.examples.calculator.MultipleArithmeticOperations,
              io.grpc.examples.calculator.ArithmeticOpResult>(
                service, METHODID_MANY_ARITHMETIC_OPERATIONS)))
        .build();
  }

  private static abstract class CalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.examples.calculator.CalculatorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Calculator");
    }
  }

  private static final class CalculatorFileDescriptorSupplier
      extends CalculatorBaseDescriptorSupplier {
    CalculatorFileDescriptorSupplier() {}
  }

  private static final class CalculatorMethodDescriptorSupplier
      extends CalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CalculatorMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CalculatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculatorFileDescriptorSupplier())
              .addMethod(getAddMethod())
              .addMethod(getSubtractMethod())
              .addMethod(getManyArithmeticOperationsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
