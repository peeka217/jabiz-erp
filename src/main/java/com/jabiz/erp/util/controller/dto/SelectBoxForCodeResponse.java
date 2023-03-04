package com.jabiz.erp.util.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.systemcode.domain.entity.SystemCode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SelectBoxForCodeResponse {

    private String code;
    private String codeName;

    public static SelectBoxForCodeResponse of(SystemCode systemCode) {
        return SelectBoxForCodeResponse.builder()
                .code(systemCode.getCode())
                .codeName(systemCode.getCodeName())
                .build();

    }
    @Builder
    public SelectBoxForCodeResponse(String code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

}
