package com.jabiz.erp.business.domain.constant;

import lombok.Getter;

@Getter
public enum BusinessPartCode {

    TS00("TS00", "오전"),
    TS01("TS01", "오전(상차)"),
    TS02("TS02", "오전(하차)"),
    TS03("BZ03", "오후"),
    TS04("TS04", "오후(MP)"),
    TS05("TS05", "풀타임");

    private final String code;
    private final String name;

    private BusinessPartCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
