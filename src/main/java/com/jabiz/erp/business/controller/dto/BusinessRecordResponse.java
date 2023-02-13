package com.jabiz.erp.business.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    private String stateCode;
    private String state;

    private String siteCode;
    private String site;
    private LocalDate timeStamp;
    private String timeSlotCode;
    private String timeSlot;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer basicTime;
    private Integer overtime;

    private String basicSalary;
    private String extraSalary;
    private String commission;
    private String paymentAmount;



    private Long workerId;
    private String realName;
    private String residentRegistrationNumber;
    private String bank;
    private String bankCode;
    private String accountNumber;
    private String accountHolder;

    private String note;

    private Long agentId;
    private String agentName;
    private Long approverId;
    private String approverName;


    private String salesAmount;
    private String vatAmount;
    private String depositAmount;
    private String netProfitAmount;

    public static BusinessRecordResponse of(BusinessRecord businessRecord) {
        return BusinessRecordResponse.builder()
                .id(businessRecord.getId())

                .stateCode(businessRecord.getStateCode())
                .state(businessRecord.getState())

                .siteCode(businessRecord.getSiteCode())
                .site(businessRecord.getSite())
                .timeStamp(businessRecord.getTimeStamp())
                .timeSlotCode(businessRecord.getTimeSlotCode())
                .timeSlot(businessRecord.getTimeSlot())
                .startTime(businessRecord.getStartTime())
                .endTime(businessRecord.getEndTime())
                .basicTime(businessRecord.getBasicTime())
                .overtime(businessRecord.getOvertime())

                .basicSalary(businessRecord.getBasicSalary())
                .extraSalary(businessRecord.getExtraSalary())
                .commission(businessRecord.getCommission())
                .paymentAmount(businessRecord.getPaymentAmount())

                .workerId(businessRecord.getWorkerId())
                .realName(businessRecord.getRealName())
                .residentRegistrationNumber(businessRecord.getResidentRegistrationNumber())
                .bankCode(businessRecord.getBankCode())
                .bank(businessRecord.getBank())
                .accountNumber(businessRecord.getAccountNumber())
                .accountHolder(businessRecord.getAccountHolder())

                .note(businessRecord.getNote())

                .agentId(businessRecord.getAgentId())
                .agentName(businessRecord.getAgentName())
                .approverId(businessRecord.getApproverId())
                .approverName(businessRecord.getApproverName())

                .vatAmount(businessRecord.getVatAmount())
                .depositAmount(businessRecord.getDepositAmount())
                .netProfitAmount(businessRecord.getNetProfitAmount())

                .build();
    }

    @Builder
    public BusinessRecordResponse(Long id,
                                  String stateCode, String state,

                                  String siteCode, String site, LocalDate timeStamp, String timeSlot, String timeSlotCode,
                                  LocalTime startTime, LocalTime endTime, Integer basicTime, Integer overtime,

                                  String basicSalary, String extraSalary, String commission, String paymentAmount,

                                  Long workerId, String realName, String residentRegistrationNumber,
                                  String bankCode, String bank, String accountNumber, String accountHolder,

                                  String note,

                                  Long agentId, String agentName, Long approverId, String approverName,

                                  String salesAmount, String vatAmount, String depositAmount,  String netProfitAmount) {
        this.id = id;

        this.stateCode = stateCode;
        this.state = state;

        this.siteCode = siteCode;
        this.site = site;
        this.timeStamp = timeStamp;
        this.timeSlotCode = timeSlotCode;
        this.timeSlot = timeSlot;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basicTime = basicTime;
        this.overtime = overtime;

        this.basicSalary = basicSalary;
        this.extraSalary = extraSalary;
        this.commission = commission;
        this.paymentAmount = paymentAmount;

        this.workerId = workerId;
        this.realName = realName;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.bankCode = bankCode;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;

        this.note = note;

        this.agentId = agentId;
        this.agentName = agentName;
        this.approverId = approverId;
        this.approverName = approverName;


        this.salesAmount = salesAmount;
        this.vatAmount = vatAmount;
        this.depositAmount = depositAmount;
        this.netProfitAmount = netProfitAmount;
    }
}
