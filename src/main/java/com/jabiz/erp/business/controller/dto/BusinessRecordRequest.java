package com.jabiz.erp.business.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BusinessRecordRequest {

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

    public BusinessRecord toBusinessRecord() {
        return BusinessRecord.builder()
                .id(this.id)
                .stateCode(this.stateCode)
                .state(this.state)

                .siteCode(this.siteCode)
                .site(this.site)
                .timeStamp(this.timeStamp)
                .timeSlotCode(this.timeSlotCode)
                .timeSlot(this.timeSlot)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .basicTime(this.basicTime)
                .overtime(this.overtime)

                .basicSalary(this.basicSalary)
                .extraSalary(this.extraSalary)
                .commission(this.commission)
                .paymentAmount(this.paymentAmount)

                .workerId(this.workerId)
                .realName(this.realName)
                .residentRegistrationNumber(this.residentRegistrationNumber)
                .bank(this.bank)
                .bankCode(this.bankCode)
                .accountNumber(this.accountNumber)
                .accountHolder(this.accountHolder)

                .note(this.note)
                .agentId(this.agentId)
                .agentName(this.agentName)
                .approverId(this.approverId)
                .approverName(this.approverName)

                .salesAmount(this.salesAmount)
                .vatAmount(this.vatAmount)
                .depositAmount(this.depositAmount)
                .netProfitAmount(this.netProfitAmount)
                .build();
    }

    public BusinessRecord toBusinessRecordForRegistration(BusinessRecord businessRecord) {
        businessRecord.setStateCode("RG");
        businessRecord.setState("등록");
        businessRecord.setAgentId(SecurityUtil.getMemberId());
        businessRecord.setAgentName(SecurityUtil.getMemberRealname());

        return businessRecord;
    }

}
