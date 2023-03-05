package com.jabiz.erp.dashboard.service;

import com.google.common.collect.ImmutableList;
import com.jabiz.erp.business.infra.BusinessRecordRepository;
import com.jabiz.erp.dashboard.controller.dto.DashboardSearchCriteria;
import com.jabiz.erp.dashboard.domain.entity.Dashboard;
import com.jabiz.erp.dashboard.domain.entity.dto.DashboardResponse;
import com.jabiz.erp.exception.InvalidDataException;
import com.jabiz.erp.site.controller.dto.SiteResponse;
import com.jabiz.erp.site.domain.entity.Site;
import com.jabiz.erp.site.infra.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final BusinessRecordRepository businessRecordRepository;
    private final SiteRepository siteRepository;


    @Transactional(readOnly = true)
    public Map<String, DashboardResponse> searchDashboard(DashboardSearchCriteria searchCriteria) {
        List<Site> siteEntities = siteRepository.findByUseYnEquals("Y")
                .orElseThrow(InvalidDataException::new);

        List<Long> sites = new ArrayList<>();
        Map<String, DashboardResponse> dashboardResponseMap = new HashMap<>();
        dashboardResponseMap.put("total_count", DashboardResponse.builder()
                .numberOfTo(0)
                .numberOfRegistrations(0)
                .numberOfSubmissions(0)
                .numberOfPayments(0)
                .build());
        siteEntities.forEach(siteEntity -> {
            sites.add(siteEntity.getId());
            dashboardResponseMap.put(siteEntity.getSiteName(), DashboardResponse.builder()
                    .numberOfTo(0)
                    .numberOfRegistrations(0)
                    .numberOfSubmissions(0)
                    .numberOfPayments(0)
                    .build());
        });

        List<Dashboard> dashboards = businessRecordRepository
                .findBySiteIdsInGroupByBusinessState(sites, searchCriteria.getWorkedAt());

        dashboards.forEach(dashboard -> {
            DashboardResponse totalCount = dashboardResponseMap.get("total_count");
            DashboardResponse dashboardResponse = dashboardResponseMap.get(dashboard.getSiteName());

            totalCount.setNumberOfTo(totalCount.getNumberOfTo() + dashboard.getRecordCount().intValue());
            dashboardResponse.setNumberOfTo(dashboardResponse.getNumberOfTo() + dashboard.getRecordCount().intValue());
            switch (dashboard.getBusinessStateCode()) {
                case BS01:
                    totalCount.setNumberOfRegistrations(
                            totalCount.getNumberOfRegistrations() + dashboard.getRecordCount().intValue());
                    dashboardResponse.setNumberOfRegistrations(
                            dashboardResponse.getNumberOfRegistrations() + dashboard.getRecordCount().intValue());
                    break;
                case BS02:
                    totalCount.setNumberOfSubmissions(
                            totalCount.getNumberOfSubmissions() + dashboard.getRecordCount().intValue());
                    dashboardResponse.setNumberOfSubmissions(
                            dashboardResponse.getNumberOfSubmissions() + dashboard.getRecordCount().intValue());
                    break;
                case BS03:
                    totalCount.setNumberOfPayments(
                            totalCount.getNumberOfPayments() + dashboard.getRecordCount().intValue());
                    dashboardResponse.setNumberOfPayments(
                            dashboardResponse.getNumberOfPayments() + dashboard.getRecordCount().intValue());
                    break;
            }
        });

        return dashboardResponseMap;
    }
}
