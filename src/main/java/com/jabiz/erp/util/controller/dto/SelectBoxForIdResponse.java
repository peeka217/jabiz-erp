package com.jabiz.erp.util.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SelectBoxForIdResponse {

    private Long id;
    private String idName;


    public static SelectBoxForIdResponse of(Long id, String idName) {
        return SelectBoxForIdResponse.builder()
                .id(id)
                .idName(idName)
                .build();
    }

    @Builder
    public SelectBoxForIdResponse(Long id, String idName) {
        this.id = id;
        this.idName = idName;
    }
}
