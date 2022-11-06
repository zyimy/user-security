package com.cloud.security.carservice.exception;

public class CarException extends RuntimeException{

  private final ErrorCode errorCode;

  public CarException(ErrorCode errorCode){
    this.errorCode = errorCode;
  }

  public CarException(ErrorCode errorCode,String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public CarException(ErrorCode errorCode,String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }



  public ErrorCode getErrorCode() {
    return errorCode;
  }
}

