package com.jabiz.erp.business.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
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

    private Long businessRecordId;

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


    private String salesAmount;
    private String vatAmount;
    private String depositAmount;
    private String netProfitAmount;

    private BusinessStateCode nextBusinessStateCode;

    public BusinessRecord toBusinessRecordForBusinessStateChange() {
        return BusinessRecord.builder()
                .id(this.businessRecordId)

                .businessStateCode(this.nextBusinessStateCode)
                .businessStateName(this.nextBusinessStateCode.getName())

                .build();
    }
    public BusinessRecord toBusinessRecord() {
        return BusinessRecord.builder()
                .id(this.businessRecordId)

                .businessStateCode(this.businessStateCode)
                .businessStateName(this.businessStateName)

                .siteId(this.siteId)
                .siteName(this.siteName)
                .workedAt(this.workedAt)
                .businessPartCode(this.businessPartCode)
                .businessPartName(this.businessPartName)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .basicTime(this.basicTime)
                .overtime(this.overtime)

                .basicSalary(this.basicSalary)
                .extraSalary(this.extraSalary)
                .commission(this.commission)
                .paymentAmount(this.paymentAmount)

                .workerId(this.workerId)
                .workerRealName(this.workerRealName)
                .residentRegistrationNumber(this.residentRegistrationNumber)
                .bankCode(this.bankCode)
                .bankName(this.bankName)
                .accountNumber(this.accountNumber)
                .accountHolder(this.accountHolder)

                .note(this.note)
                .agentId(this.agentId)
                .agentName(this.agentName)
                .approverId(this.approverId)
                .approverName(this.approverName)

                .build();
    }

}
