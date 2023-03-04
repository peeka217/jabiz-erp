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
    public ResponseEntity<ErrorResponse> handleUnauthenticatedAccessException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHENTICATED_ACCESS);

        return new ResponseEntity<>(errorResponse, ErrorCode.UNAUTHENTICATED_ACCESS.getCode());
    }

    @ExceptionHandler(value = UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHORIZED_ACCESS);

        return new ResponseEntity<>(errorResponse, ErrorCode.UNAUTHORIZED_ACCESS.getCode());
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.RESOURCE_NOT_FOUND);

        return new ResponseEntity<>(errorResponse, ErrorCode.RESOURCE_NOT_FOUND.getCode());
    }

    @ExceptionHandler(value = StateConflictException.class)
    public ResponseEntity<ErrorResponse> handleStateConflictException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.STATE_CONFLICT);

        return new ResponseEntity<>(errorResponse, ErrorCode.STATE_CONFLICT.getCode());
    }

    @ExceptionHandler(value = InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_DATA);

        return new ResponseEntity<>(errorResponse, ErrorCode.INVALID_DATA.getCode());
    }

}
