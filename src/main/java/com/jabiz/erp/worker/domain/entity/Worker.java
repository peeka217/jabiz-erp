package com.jabiz.erp.worker.domain.entity;

import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Worker extends PrimitiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realName;

    private String nationalityCode;
    private String country;
    private String countryCode;
    private String gender;

    private String birthday;
    private String residentRegistrationNumber;
    private String residenceStatusCode;

    private String stayFrom;
    private String stayTo;

    private String phoneNumber;
    private String bank;
    private String bankCode;
    private String accountNumber;
    private String accountHolder;

    private String workReportYn;
    private String workRecordId;

    private String deletedYn;

    @Builder
    public Worker(Long id,
                  String realName, String nationalityCode, String country, String countryCode, String gender,
                  String birthday, String residentRegistrationNumber, String residenceStatusCode,
                  String stayFrom, String stayTo,
                  String phoneNumber, String bank, String bankCode, String accountNumber, String accountHolder,
                  String workReportYn, String workRecordId,
                  String deletedYn) {

        this.id = id;
        this.realName = realName;
        this.country = country;
        this.nationalityCode = nationalityCode;
        this.countryCode = countryCode;
        this.gender = gender;
        this.birthday = birthday;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.residenceStatusCode = residenceStatusCode;
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;
        this.phoneNumber = phoneNumber;
        this.bank = bank;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.workReportYn = workReportYn;
        this.workRecordId = workRecordId;
        this.deletedYn = deletedYn;
    }

}
