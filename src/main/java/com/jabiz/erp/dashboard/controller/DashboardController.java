package com.jabiz.erp.dashboard.controller;

import com.jabiz.erp.dashboard.controller.dto.DashboardSearchCriteria;
import com.jabiz.erp.dashboard.domain.entity.dto.DashboardResponse;
import com.jabiz.erp.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public Map<String, DashboardResponse> searchDashboard(
            @RequestParam(value = "worked_at") LocalDate workedAt) {
        return dashboardService.searchDashboard(
                DashboardSearchCriteria.builder()
                        .workedAt(workedAt)
                        .build());
    }

}
