package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends RuntimeException {

    private ErrorCode errorCode;

    public UnauthorizedAccessException() {
        this.errorCode = ErrorCode.UNAUTHORIZED_ACCESS;
    }
}
