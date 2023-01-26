package com.jabiz.erp.exception;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ErrorResponse {

    @Value("${spring.application.name}")
    private static String APPLICATION;
    private String code;
    private String message;

    ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

}
