// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator/calculator.proto

// Protobuf Java Version: 3.25.1
package io.grpc.examples.calculator;

public interface ArithmeticOperationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:calculator.ArithmeticOperation)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string operation = 1;</code>
   * @return The operation.
   */
  java.lang.String getOperation();
  /**
   * <code>string operation = 1;</code>
   * @return The bytes for operation.
   */
  com.google.protobuf.ByteString
      getOperationBytes();

  /**
   * <code>.calculator.ArithmeticOpArguments arguments = 2;</code>
   * @return Whether the arguments field is set.
   */
  boolean hasArguments();
  /**
   * <code>.calculator.ArithmeticOpArguments arguments = 2;</code>
   * @return The arguments.
   */
  io.grpc.examples.calculator.ArithmeticOpArguments getArguments();
  /**
   * <code>.calculator.ArithmeticOpArguments arguments = 2;</code>
   */
  io.grpc.examples.calculator.ArithmeticOpArgumentsOrBuilder getArgumentsOrBuilder();
}
