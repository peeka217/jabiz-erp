package com.jabiz.erp.dashboard.domain.entity.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DashboardResponse {

    private Integer numberOfTo;
    private Integer numberOfRegistrations;
    private Integer numberOfSubmissions;
    private Integer numberOfPayments;

    @Builder
    public DashboardResponse(Integer numberOfTo,
                             Integer numberOfRegistrations,
                             Integer numberOfSubmissions,
                             Integer numberOfPayments) {
        this.numberOfTo = numberOfTo;
        this.numberOfRegistrations = numberOfRegistrations;
        this.numberOfSubmissions = numberOfSubmissions;
        this.numberOfPayments = numberOfPayments;
    }

}
