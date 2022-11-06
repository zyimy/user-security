package com.cloud.security.bikeservice.exception;

public class BikeException extends RuntimeException{

  private final ErrorCode errorCode;

  public BikeException(ErrorCode errorCode){
    this.errorCode = errorCode;
  }

  public BikeException(ErrorCode errorCode,String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public BikeException(ErrorCode errorCode,String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }



  public ErrorCode getErrorCode() {
    return errorCode;
  }
}

