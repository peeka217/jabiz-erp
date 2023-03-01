package com.jabiz.erp.report.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.report.domain.entity.Funds;
import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FundingStatusResponse {

    private List<FundsDailyRecordResponse> fundsDailyRecords;
    private List<FundsRecordResponse> fundsRecordsForDeposit;
    private List<FundsRecordResponse> fundsRecordsForWithdrawal;

    @Builder
    public FundingStatusResponse(List<FundsDailyRecordResponse> fundsDailyRecords,
                                 List<FundsRecordResponse> fundsRecordsForDeposit,
                                 List<FundsRecordResponse> fundsRecordsForWithdrawal) {
        this.fundsDailyRecords = fundsDailyRecords;
        this.fundsRecordsForDeposit = fundsRecordsForDeposit;
        this.fundsRecordsForWithdrawal = fundsRecordsForWithdrawal;
    }

}
