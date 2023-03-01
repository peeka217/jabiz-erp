package com.jabiz.erp.business.domain.constant;

import lombok.Getter;

@Getter
public enum BusinessStateCode {

    BZ00("BZ00", "미등록"),
    BZ01("BZ01", "등록"),
    BZ02("BZ02", "상신"),
    BZ03("BZ03", "지급"),
    BZ04("BZ04", "보류");

    private final String code;
    private final String name;

    private BusinessStateCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
