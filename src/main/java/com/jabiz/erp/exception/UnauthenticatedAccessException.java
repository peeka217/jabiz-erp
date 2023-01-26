package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class UnauthenticatedAccessException extends RuntimeException {

    private ErrorCode errorCode;

    public UnauthenticatedAccessException() {
        this.errorCode = ErrorCode.NOT_FOUND;
    }

}
