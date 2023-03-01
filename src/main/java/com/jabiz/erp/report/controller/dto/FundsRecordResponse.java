package com.jabiz.erp.report.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.report.domain.entity.FundsRecord;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FundsRecordResponse {

    private Long id;

    private Long companyId;
    private String companyName;
    private String fundsCategoryCode;
    private String fundsCategoryName;
    private Long fundsId;
    private String fundsName;


    private LocalDate tradedAt;
    private String trader;
    private String contents;

    private String transactionCode;
    private String transactionAmount;

    public static FundsRecordResponse of(FundsRecord fundsRecord) {
        return FundsRecordResponse.builder()
                .id(fundsRecord.getId())
                .companyId(fundsRecord.getCompanyId())
                .companyName(fundsRecord.getCompanyName())
                .fundsCategoryCode(fundsRecord.getFundsCategoryName())
                .fundsCategoryName(fundsRecord.getFundsCategoryName())
                .fundsId(fundsRecord.getFundsId())
                .fundsName(fundsRecord.getFundsName())

                .tradedAt(fundsRecord.getTradedAt())
                .trader(fundsRecord.getTrader())
                .contents(fundsRecord.getContents())
                .transactionCode(fundsRecord.getTransactionCode())
                .transactionAmount(fundsRecord.getTransactionAmount())
                .build();
    }

    @Builder
    public FundsRecordResponse(Long id,
                               Long companyId, String companyName,
                               String fundsCategoryCode, String fundsCategoryName,
                               Long fundsId, String fundsName,
                               LocalDate tradedAt, String trader, String contents,
                               String transactionCode, String transactionAmount) {

        this.id = id;

        this.companyId = companyId;
        this.companyName = companyName;
        this.fundsCategoryCode = fundsCategoryCode;
        this.fundsCategoryName = fundsCategoryName;
        this.fundsId = fundsId;
        this.fundsName = fundsName;

        this.tradedAt = tradedAt;
        this.trader = trader;
        this.contents = contents;
        this.transactionCode = transactionCode;
        this.transactionAmount = transactionAmount;
    }


}
