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

    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "UNAUTHORIZED_ACCESS", "접근권한이 없습니다."),

    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", "요청하신 리소스는 존재하지 않습니다."),

    STATE_CONFLICT(HttpStatus.CONFLICT, "STATE_CONFLICT", "클라이언트의 요청이 서버의 상태에 반합니다." ),

    INVALID_DATA(HttpStatus.BAD_REQUEST, "INVALID_DATA", "유효하지 않은 요청 데이터 입니다.");



    private final HttpStatus code;
    private final String status;
    private final String message;

    private ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.code = httpStatus;
        this.status = code;
        this.message = message;
    }

}
