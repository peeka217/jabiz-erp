package com.jabiz.erp.business.domain.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public enum BusinessStateCode {

    BS00("BS00", "미등록"),
    BS01("BS01", "등록"),
    BS02("BS02", "상신"),
    BS03("BS03", "지급"),
    BS04("BS04", "보류");

    private final String code;
    private final String name;

    private BusinessStateCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Boolean isManipulableForRegistration(BusinessStateCode stateCode) {
        if (stateCode != BusinessStateCode.BS00 && stateCode != BusinessStateCode.BS01) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isChangeableState(BusinessStateCode currentState, BusinessStateCode nextState) {
        Map<BusinessStateCode, List<BusinessStateCode>> stateChangeProcedure =  Map.of(
                BusinessStateCode.BS02, Arrays.asList(BusinessStateCode.BS01),
                BusinessStateCode.BS03, Arrays.asList(BusinessStateCode.BS02),
                BusinessStateCode.BS04, Arrays.asList(BusinessStateCode.BS02, BusinessStateCode.BS03)
        );

        return stateChangeProcedure.get(nextState).contains(currentState);
    }

}
