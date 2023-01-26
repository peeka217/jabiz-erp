package com.jabiz.erp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.SYSTEM_FAILURE);

        return new ResponseEntity<>(errorResponse, ErrorCode.SYSTEM_FAILURE.getCode());
    }

    @ExceptionHandler(value = UnauthenticatedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHENTICATED_ACCESS);

        return new ResponseEntity<>(errorResponse, ErrorCode.UNAUTHENTICATED_ACCESS.getCode());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NOT_FOUND);

        return new ResponseEntity<>(errorResponse, ErrorCode.NOT_FOUND.getCode());
    }

}
