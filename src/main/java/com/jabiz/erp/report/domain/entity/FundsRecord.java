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
public class FundsRecord extends PrimitiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siteCode;
    private String site;
    private String fundsCategory;
    private String bankInstitution;


    private LocalDate timeStamp;
    private String trader;
    private String contents;
    private String depositAmount;
    private String withdrawalAmount;

    @Builder
    public FundsRecord(Long id,
                       String siteCode, String site, String fundsCategory, String bankInstitution,
                       LocalDate timeStamp, String trader, String contents,
                       String depositAmount, String withdrawalAmount) {

        this.id = id;

        this.siteCode = siteCode;
        this.site = site;
        this.fundsCategory = fundsCategory;
        this.bankInstitution = bankInstitution;

        this.timeStamp = timeStamp;
        this.trader = trader;
        this.contents = contents;

        this.depositAmount = depositAmount;
        this.withdrawalAmount = withdrawalAmount;

    }

}
