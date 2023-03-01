package com.jabiz.erp.business.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BusinessRecordResponse {

    private Long id;

    private BusinessStateCode businessStateCode;
    private String businessStateName;

    private Long siteId;
    private String siteName;
    private LocalDate workedAt;
    private BusinessPartCode businessPartCode;
    private String businessPartName;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer basicTime;
    private Integer overtime;

    private String basicSalary;
    private String extraSalary;
    private String commission;
    private String paymentAmount;

    private Long workerId;
    private String workerRealName;
    private String residentRegistrationNumber;
    private String bankCode;
    private String bankName;
    private String accountNumber;
    private String accountHolder;

    private String note;

    private Long agentId;
    private String agentName;
    private Long approverId;
    private String approverName;

    public static BusinessRecordResponse of(BusinessRecord businessRecord) {
        return BusinessRecordResponse.builder()
                .id(businessRecord.getId())

                .businessStateCode(businessRecord.getBusinessStateCode())
                .businessStateName(businessRecord.getBusinessStateName())

                .siteId(businessRecord.getSiteId())
                .siteName(businessRecord.getSiteName())
                .workedAt(businessRecord.getWorkedAt())
                .businessPartCode(businessRecord.getBusinessPartCode())
                .businessPartName(businessRecord.getBusinessPartName())
                .startTime(businessRecord.getStartTime())
                .endTime(businessRecord.getEndTime())
                .basicTime(businessRecord.getBasicTime())
                .overtime(businessRecord.getOvertime())

                .basicSalary(businessRecord.getBasicSalary())
                .extraSalary(businessRecord.getExtraSalary())
                .commission(businessRecord.getCommission())
                .paymentAmount(businessRecord.getPaymentAmount())

                .workerId(businessRecord.getWorkerId())
                .workerRealName(businessRecord.getWorkerRealName())
                .residentRegistrationNumber(businessRecord.getResidentRegistrationNumber())
                .bankName(businessRecord.getBankName())
                .bankCode(businessRecord.getBankCode())
                .accountNumber(businessRecord.getAccountNumber())
                .accountHolder(businessRecord.getAccountHolder())

                .note(businessRecord.getNote())

                .agentId(businessRecord.getAgentId())
                .agentName(businessRecord.getAgentName())
                .approverId(businessRecord.getApproverId())
                .approverName(businessRecord.getApproverName())

                .build();
    }

    @Builder
    public BusinessRecordResponse(Long id,
                                  BusinessStateCode businessStateCode, String businessStateName,

                                  Long siteId, String siteName, LocalDate workedAt, BusinessPartCode businessPartCode, String businessPartName,
                                  LocalTime startTime, LocalTime endTime, Integer basicTime, Integer overtime,

                                  String basicSalary, String extraSalary, String commission, String paymentAmount,

                                  Long workerId, String workerRealName, String residentRegistrationNumber,
                                  String bankCode, String bankName, String accountNumber, String accountHolder,

                                  String note,

                                  Long agentId, String agentName, Long approverId, String approverName) {
        this.id = id;

        this.businessStateCode = businessStateCode;
        this.businessStateName = businessStateName;

        this.siteId = siteId;
        this.siteName = siteName;
        this.workedAt = workedAt;
        this.businessPartCode = businessPartCode;
        this.businessPartName = businessPartName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basicTime = basicTime;
        this.overtime = overtime;

        this.basicSalary = basicSalary;
        this.extraSalary = extraSalary;
        this.commission = commission;
        this.paymentAmount = paymentAmount;

        this.workerId = workerId;
        this.workerRealName = workerRealName;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;

        this.note = note;

        this.agentId = agentId;
        this.agentName = agentName;
        this.approverId = approverId;
        this.approverName = approverName;
    }
}
