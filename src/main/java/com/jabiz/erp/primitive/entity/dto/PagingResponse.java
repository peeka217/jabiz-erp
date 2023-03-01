package com.jabiz.erp.primitive.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagingResponse {

    private Integer totalPagesCount;

    private List<Object> pages;

    @Builder
    public PagingResponse(Integer totalPagesCount, List<Object> pages) {
        this.totalPagesCount = totalPagesCount;
        this.pages = pages;
    }
}
