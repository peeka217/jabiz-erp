package com.jabiz.erp.report.controller.dto;

import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
import lombok.Builder;

import java.time.LocalDate;

public class FundsDailyRecordResponse {

    private Long id;

    private Long companyId;
    private String companyName;
    private String fundsCategoryCode;
    private String fundsCategoryName;
    private Long fundsId;
    private String fundsName;

    private Long lastFundsReocrdId;

    private String depositAmount;
    private String withdrawalAmount;
    private String balance;

    private LocalDate subtotalTimestamp;


    public static FundsDailyRecordResponse of(FundsDailyRecord fundsDailyRecord) {
        return FundsDailyRecordResponse.builder()
                .id(fundsDailyRecord.getId())

                .companyId(fundsDailyRecord.getCompanyId())
                .companyName(fundsDailyRecord.getCompanyName())
                .fundsCategoryCode(fundsDailyRecord.getFundsCategoryCode())
                .fundsCategoryName(fundsDailyRecord.getFundsCategoryName())
                .fundsId(fundsDailyRecord.getFundsId())
                .fundsName(fundsDailyRecord.getFundsName())

                .lastFundsReocrdId(fundsDailyRecord.getLastFundsReocrdId())

                .depositAmount(fundsDailyRecord.getDepositAmount())
                .withdrawalAmount(fundsDailyRecord.getWithdrawalAmount())
                .balance(fundsDailyRecord.getBalance())

                .build();

    }
    @Builder
    public FundsDailyRecordResponse(Long id,
                                    Long companyId, String companyName,
                                    String fundsCategoryCode, String fundsCategoryName, Long fundsId, String fundsName,
                                    Long lastFundsReocrdId,
                                    String depositAmount, String withdrawalAmount, String balance,
                                    LocalDate subtotalTimestamp) {
        this.id = id;

        this.companyId = companyId;
        this.companyName = companyName;
        this.fundsCategoryCode = fundsCategoryCode;
        this.fundsCategoryName = fundsCategoryName;
        this.fundsId = fundsId;
        this.fundsName = fundsName;

        this.lastFundsReocrdId = lastFundsReocrdId;

        this.depositAmount = depositAmount;
        this.withdrawalAmount = withdrawalAmount;
        this.balance = balance;

        this.subtotalTimestamp = subtotalTimestamp;
    }


}
