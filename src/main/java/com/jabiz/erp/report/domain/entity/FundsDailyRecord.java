package com.jabiz.erp.report.domain.entity;

import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FundsDailyRecord extends PrimitiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private LocalDate tradedAt;

    @Builder
    public FundsDailyRecord(Long id,
                            Long companyId, String companyName,
                            String fundsCategoryCode, String fundsCategoryNAme, Long fundsId, String fundsName,
                            Long lastFundsReocrdId,
                            String depositAmount, String withdrawalAmount,String balance,
                            LocalDate tradedAt) {
        this.id = id;

        this.companyId = companyId;
        this.companyName = companyName;
        this.fundsCategoryCode = fundsCategoryCode;
        this.fundsCategoryName = fundsCategoryNAme;
        this.fundsId = fundsId;
        this.fundsName = fundsName;

        this.lastFundsReocrdId = lastFundsReocrdId;

        this.depositAmount = depositAmount;
        this.withdrawalAmount = withdrawalAmount;
        this.balance = balance;

        this.tradedAt = tradedAt;
    }

}
