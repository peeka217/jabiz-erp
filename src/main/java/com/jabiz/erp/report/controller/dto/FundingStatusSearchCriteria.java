package com.jabiz.erp.report.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FundingStatusSearchCriteria {

    private Long companyId;
    private LocalDate tradedAt;
    private LocalDate FromTradedAt;
    private LocalDate ToTradedAt;
    private String trader;
    private String contents;

    private Long lastFundsReocrdId;
    private int pagingNumber;
    private int pagingSize;

    @Builder
    public FundingStatusSearchCriteria(Long companyId,
                                       LocalDate tradedAt,
                                       LocalDate FromTradedAt, LocalDate ToTradedAt,
                                       String trader, String contents,
                                       Long lastFundsReocrdId,
                                       int pagingNumber, int pagingSize) {
        this.companyId = companyId;
        this.tradedAt = tradedAt;
        this.FromTradedAt = FromTradedAt;
        this.ToTradedAt = ToTradedAt;
        this.trader = trader;
        this.contents = contents;
        this.lastFundsReocrdId = lastFundsReocrdId;
        this.pagingNumber = pagingNumber;
        this.pagingSize = pagingSize;

    }

}
