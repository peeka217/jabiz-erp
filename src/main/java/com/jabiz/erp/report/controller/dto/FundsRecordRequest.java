package com.jabiz.erp.report.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.report.domain.entity.FundsRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FundsRecordRequest {

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

    public FundsRecord toFundsRecord() {
        return FundsRecord.builder()
                .companyId(this.companyId)
                .companyName(this.companyName)
                .fundsCategoryCode(this.fundsCategoryCode)
                .fundsCategoryName(this.fundsCategoryName)
                .fundsName(this.fundsName)

                .tradedAt(this.tradedAt)
                .trader(this.trader)
                .contents(this.contents)
                .transactionCode(this.transactionCode)
                .transactionAmount(this.transactionAmount)

                .build();
    }
}
