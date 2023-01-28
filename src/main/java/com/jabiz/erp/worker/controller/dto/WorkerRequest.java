package com.jabiz.erp.worker.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.worker.domain.entity.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WorkerRequest {

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

    public Worker toWorker() {
        return Worker.builder()
                .id(this.id)
                .realName(this.realName)
                .nationalityCode(this.nationalityCode)
                .country(this.country)
                .countryCode(this.countryCode)
                .gender(this.gender)
                .birthday(this.birthday)
                .residentRegistrationNumber(this.residentRegistrationNumber)
                .residenceStatusCode(this.residenceStatusCode)
                .stayFrom(this.stayFrom)
                .stayTo(this.stayTo)
                .phoneNumber(this.phoneNumber)
                .bank(this.bank)
                .bankCode(this.bankCode)
                .accountNumber(this.accountNumber)
                .accountHolder(this.accountHolder)
                .workReportYn(this.workReportYn)
                .workRecordId(this.workRecordId)
                .deletedYn("N")
                .build();
    }

}
