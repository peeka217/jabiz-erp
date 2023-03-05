package com.jabiz.erp.dashboard.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DashboardSearchCriteria {

    private LocalDate workedAt;

    @Builder
    public DashboardSearchCriteria(LocalDate workedAt) {
        this.workedAt = workedAt;
    }
}
