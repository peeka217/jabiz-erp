package com.jabiz.erp.report.domain.entity;

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
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fundsName;
    private Long companyId;
    private String companyName;
    private String fundsCategoryCode;
    private String fundsCategoryName;

    private Long lastFundsRecordId;
    private String accountNumber;
    private String balance;

    @Builder
    public Funds(Long id, String fundsName,
                 Long companyId, String companyName,
                 String fundsCategoryCode, String fundsCategoryName,
                 Long lastFundsRecordId,
                 String accountNumber, String balance) {
        this.id = id;
        this.fundsName = fundsName;

        this.companyId = companyId;
        this.companyName = companyName;
        this.fundsCategoryCode = fundsCategoryCode;
        this.fundsCategoryName = fundsCategoryName;

        this.lastFundsRecordId = lastFundsRecordId;

        this.accountNumber = accountNumber;
        this.balance = balance;

    }
}
