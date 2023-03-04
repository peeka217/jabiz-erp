package com.jabiz.erp.primitive.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagingResponse {

    private Integer totalPagesCount;
    private List<? extends Object> pages;





    @Builder
    public PagingResponse(Integer totalPagesCount, List<? extends Object> pages) {
        this.pages = pages;
        this.totalPagesCount = totalPagesCount;
    }
}
