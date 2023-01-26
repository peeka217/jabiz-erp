package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public NotFoundException() {
        this.errorCode = ErrorCode.UNAUTHENTICATED_ACCESS;
    }

}
