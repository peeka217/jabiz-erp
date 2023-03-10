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


    @Builder
    public FundsRecord(Long id,
                       Long companyId, String companyName,
                       String fundsCategoryCode, String fundsCategoryName, Long fundsId, String fundsName,
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
