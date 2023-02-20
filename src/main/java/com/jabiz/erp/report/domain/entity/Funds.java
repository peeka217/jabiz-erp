package com.jabiz.erp.report.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siteCode;
    private String site;
    private String fundsCategory;
    private String bankInstitution;
    private String balance;


    @Builder
    public Funds(Long id,
                 String siteCode, String site, String fundsCategory, String bankInstitution,
                 String balance) {
        this.id = id;

        this.siteCode = siteCode;
        this.site = site;
        this.fundsCategory = fundsCategory;
        this.bankInstitution = bankInstitution;

        this.balance = balance;
    }
}
