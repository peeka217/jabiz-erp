package com.jabiz.erp.primitive.entity.constant;

import lombok.Getter;

@Getter
public enum StateCode {

    AC("AC", "활성화"),
    DA("DA", "비활성화"),
    WD("WD", "회원탈퇴");

    private final String code;
    private final String state;

    private StateCode(String code, String state) {
        this.code = code;
        this.state = state;
    }

}
