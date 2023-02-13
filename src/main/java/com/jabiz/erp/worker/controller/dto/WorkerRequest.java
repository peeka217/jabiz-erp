package com.jabiz.erp.worker.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.primitive.entity.constant.StateCode;
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

    public Worker toWorkerForRegistration() {
        return Worker.builder()
                .id(this.id)

                .nationalityCode(this.nationalityCode)
                .countryCode(this.countryCode)
                .country(this.country)

                .realName(this.realName)
                .gender(this.gender)
                .birthday(this.birthday)
                .residentRegistrationNumber(this.residentRegistrationNumber)
                .residenceStatusCode(this.residenceStatusCode)
                .stayFrom(this.stayFrom)
                .stayTo(this.stayTo)
                .phoneNumber(this.phoneNumber)
                .bankCode(this.bankCode)
                .bank(this.bank)
                .accountNumber(this.accountNumber)
                .accountHolder(this.accountHolder)
                .workReportYn(this.workReportYn)
                .workRecordId(this.workRecordId)
                .stateCode("WK00")
                .build();
    }

    public Worker toWorkerForState(String stateCode) {
        return Worker.builder()
                .id(this.id)
                .stateCode(stateCode)
                .build();
    }

}
