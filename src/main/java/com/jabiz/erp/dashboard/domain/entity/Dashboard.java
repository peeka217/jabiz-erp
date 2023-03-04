package com.jabiz.erp.dashboard.domain.entity;

import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data
public class Dashboard {

    private Long siteId;
    private String siteName;

    private BusinessStateCode businessStateCode;
    private String businessStateName;

    private Long recordCount;

    @Builder
    @QueryProjection
    public Dashboard(Long siteId, String siteName,
                     BusinessStateCode businessStateCode, String businessStateName,
                     Long recordCount) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.businessStateCode = businessStateCode;
        this.businessStateName = businessStateName;
        this.recordCount = recordCount;
    }

}
