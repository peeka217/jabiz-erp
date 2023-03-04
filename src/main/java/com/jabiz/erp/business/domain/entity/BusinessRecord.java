package com.jabiz.erp.business.domain.entity;

import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import com.jabiz.erp.util.SecurityUtil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessRecord extends PrimitiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BusinessStateCode businessStateCode;
    private String businessStateName;

    private Long siteId;
    private String siteName;
    private LocalDate workedAt;

    @Enumerated(value = EnumType.STRING)
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

    public BusinessRecord setAgent() {
        this.agentId = SecurityUtil.getMemberId();
        this.agentName = SecurityUtil.getMemberRealname();

        return this;
    }

    @Builder
    public BusinessRecord(Long id,
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
