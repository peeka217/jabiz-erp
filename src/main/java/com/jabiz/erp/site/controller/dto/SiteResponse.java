package com.jabiz.erp.site.controller.dto;

import com.jabiz.erp.site.domain.entity.Site;
import lombok.Builder;
import lombok.Data;

@Data
public class SiteResponse {

    Long siteId;
    String siteName;


    public static SiteResponse of(Site site) {
        return SiteResponse.builder()
                .siteId(site.getId())
                .siteName(site.getSiteName())
                .build();
    }
    @Builder
    public SiteResponse(Long siteId, String siteName) {
        this.siteId = siteId;
        this.siteName = siteName;
    }

}
