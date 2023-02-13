package com.jabiz.erp.worker.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.worker.domain.entity.Worker;
import lombok.*;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WorkerResponse {

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

    public static WorkerResponse of(Worker worker) {
        return WorkerResponse.builder()
                .id(worker.getId())

                .nationalityCode(worker.getNationalityCode())
                .countryCode(worker.getCountryCode())
                .country(worker.getCountry())
                .realName(worker.getRealName())

                .gender(worker.getGender())

                .birthday(worker.getBirthday())
                .residentRegistrationNumber(worker.getResidentRegistrationNumber())
                .residenceStatusCode(worker.getResidenceStatusCode())
                .stayFrom(worker.getStayFrom())
                .stayTo(worker.getStayTo())

                .phoneNumber(worker.getPhoneNumber())
                .bank(worker.getBank())
                .bankCode(worker.getBankCode())
                .accountNumber(worker.getAccountNumber())
                .accountHolder(worker.getAccountHolder())

                .workReportYn(worker.getWorkReportYn())
                .workRecordId(worker.getWorkRecordId())

                .build();
    }

    @Builder
    public WorkerResponse(Long id,
                          String nationalityCode, String country, String countryCode,
                          String realName, String gender,
                          String birthday, String residentRegistrationNumber, String residenceStatusCode,
                          String stayFrom, String stayTo,
                          String phoneNumber,
                          String bankCode, String bank, String accountNumber, String accountHolder,
                          String workReportYn, String workRecordId) {
        this.id = id;

        this.nationalityCode = nationalityCode;
        this.country = country;
        this.countryCode = countryCode;

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
    }

}
