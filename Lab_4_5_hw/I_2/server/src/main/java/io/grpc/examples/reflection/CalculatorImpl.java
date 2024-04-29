package io.grpc.examples.reflection;

import io.grpc.examples.calculator.*;

import java.util.List;

public class CalculatorImpl extends CalculatorGrpc.CalculatorImplBase
{
    @Override
    public void add(ArithmeticOpArguments request,
                    io.grpc.stub.StreamObserver<ArithmeticOpResult> responseObserver)
    {
        System.out.println("addRequest (" + request.getArg1() + ", " + request.getArg2() +")");
        double val = request.getArg1() + request.getArg2();
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
        if(request.getArg1() > 100 && request.getArg2() > 100) try { Thread.sleep(5000); } catch(java.lang.InterruptedException ex) { }
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(ArithmeticOpArguments request,
                         io.grpc.stub.StreamObserver<ArithmeticOpResult> responseObserver)
    {
        System.out.println("subtractRequest (" + request.getArg1() + ", " + request.getArg2() +")");
        double val = request.getArg1() - request.getArg2();
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void manyArithmeticOperations (MultipleArithmeticOperations request,
                                          io.grpc.stub.StreamObserver<ArithmeticOpResult> responseObserver)
    {
        List<ArithmeticOperation> operations = request.getOperationsList();
        double res = 0;
        for (ArithmeticOperation operation : operations)
        {
            System.out.println("Operation: " + operation.getOperation());
            System.out.println("Operation: " + operation.getArguments());
            switch (operation.getOperation())
            {
                case "+":
                    res += operation.getArguments().getArg1() + operation.getArguments().getArg2();
                    break;
                case "-":
                    res += operation.getArguments().getArg1() - operation.getArguments().getArg2();
                    break;
                case "*":
                    res += operation.getArguments().getArg1() * operation.getArguments().getArg2();
                    break;
                case "/":
                    res += operation.getArguments().getArg1() / operation.getArguments().getArg2();
                    break;
            }
        }
        ArithmeticOpResult result = ArithmeticOpResult.newBuilder().setRes(res).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();

    }

}
