package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public ResourceNotFoundException() {
        this.errorCode = ErrorCode.RESOURCE_NOT_FOUND;
    }

}
