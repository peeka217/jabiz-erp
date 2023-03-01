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


    private String salesAmount;
    private String vatAmount;
    private String depositAmount;
    private String netProfitAmount;

    public BusinessRecord toBusinessRecord() {
        return BusinessRecord.builder()
                .id(this.id)

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

//    public BusinessRecord toSubmitStatus(BusinessRecord businessRecord) {
//        businessRecord.setBusinessStateCode(BusinessStateCode.BZ02.getCode());
//        businessRecord.setBusinessStateName(BusinessStateCode.BZ02.getName());
//        businessRecord.setAgentId(SecurityUtil.getMemberId());
//        businessRecord.setAgentName(SecurityUtil.getMemberRealname());
//
//        return businessRecord;
//    }
//
//    public BusinessRecord toPaymentStatus(BusinessRecord businessRecord) {
//        businessRecord.setBusinessStateCode(Busi);
//        businessRecord.setBusinessStateName("지급");
//        businessRecord.setAgentId(SecurityUtil.getMemberId());
//        businessRecord.setAgentName(SecurityUtil.getMemberRealname());
//
//        return businessRecord;
//    }
//
//    public BusinessRecord toHoldStatus(BusinessRecord businessRecord) {
//        businessRecord.setBusinessStateCode("BZ04");
//        businessRecord.setBusinessStateName("보류");
//        businessRecord.setAgentId(SecurityUtil.getMemberId());
//        businessRecord.setAgentName(SecurityUtil.getMemberRealname());
//
//        return businessRecord;
//    }

}
