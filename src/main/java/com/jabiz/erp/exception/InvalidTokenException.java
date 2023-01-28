package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private ErrorCode errorCode;

    public InvalidTokenException() {
        this.errorCode = ErrorCode.INVALID_TOKEN;
    }

}
