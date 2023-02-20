package com.jabiz.erp.exception;

public class InvalidDataException extends RuntimeException {

    private ErrorCode errorCode;

    public InvalidDataException() {
        this.errorCode = ErrorCode.INVALID_DATA;
    }
}
