package com.jabiz.erp.exception;

import lombok.Getter;

@Getter
public class StateConflictException extends RuntimeException {

    private ErrorCode errorCode;

    public StateConflictException() {
        this.errorCode = ErrorCode.STATE_CONFLICT;
    }

}
