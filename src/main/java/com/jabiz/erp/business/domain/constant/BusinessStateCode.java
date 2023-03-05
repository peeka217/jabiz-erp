package com.jabiz.erp.business.domain.constant;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
        Map<BusinessStateCode, List<BusinessStateCode>> stateChangeProcedure
                = ImmutableMap.<BusinessStateCode, List<BusinessStateCode>>builder()
                .put(BusinessStateCode.BS02, ImmutableList.<BusinessStateCode>builder()
                        .add(BusinessStateCode.BS01)
                        .build())
                .put(BusinessStateCode.BS03, ImmutableList.<BusinessStateCode>builder().
                        add(BusinessStateCode.BS02)
                        .build())
                .put(BusinessStateCode.BS04, ImmutableList.<BusinessStateCode>builder()
                        .add(BusinessStateCode.BS02)
                        .add(BusinessStateCode.BS03)
                        .build())
                .build();

        return stateChangeProcedure.get(nextState).contains(currentState);
    }

}
