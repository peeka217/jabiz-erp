package com.jabiz.erp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * 5XX
     */
    SYSTEM_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "SYSTEM_FAILURE", "시스템 에러"),

    /**
     * 4XX
     */
    UNAUTHENTICATED_ACCESS(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED_ACCESS", "인증이 필요한 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "요청한 정보를 찾을 수 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    DUPLICATION_ERROR(HttpStatus.CONFLICT, "DUPLICATION_ERROR", "중복이 허용되지 않는 파라미터입니다." );


    private final HttpStatus code;
    private final String status;
    private final String message;

    private ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.code = httpStatus;
        this.status = code;
        this.message = message;
    }

}
