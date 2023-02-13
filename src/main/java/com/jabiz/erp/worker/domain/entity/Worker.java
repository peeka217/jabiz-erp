package com.jabiz.erp.worker.domain.entity;

import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import com.jabiz.erp.primitive.entity.constant.StateCode;
import jakarta.persistence.*;
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

    private String nationalityCode;
    private String countryCode;
    private String country;

    private String realName;
    private String gender;
    private String birthday;
    private String residentRegistrationNumber;
    private String residenceStatusCode;

    private String stayFrom;
    private String stayTo;

    private String phoneNumber;
    private String bankCode;
    private String bank;
    private String accountNumber;
    private String accountHolder;

    private String workReportYn;
    private String workRecordId;
    private String stateCode;

    @Builder
    public Worker(Long id,
                  String nationalityCode, String countryCode, String country,
                  String realName,  String gender, String birthday, String residentRegistrationNumber,
                  String residenceStatusCode, String stayFrom, String stayTo,
                  String phoneNumber, String bank, String bankCode, String accountNumber, String accountHolder,
                  String workReportYn, String workRecordId,
                  String stateCode) {

        this.id = id;

        this.nationalityCode = nationalityCode;
        this.countryCode = countryCode;
        this.country = country;

        this.realName = realName;
        this.gender = gender;
        this.birthday = birthday;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.residenceStatusCode = residenceStatusCode;
        this.stayFrom = stayFrom;
        this.stayTo = stayTo;

        this.phoneNumber = phoneNumber;
        this.bankCode = bankCode;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.workReportYn = workReportYn;
        this.workRecordId = workRecordId;

        this.stateCode = stateCode;
    }

}
