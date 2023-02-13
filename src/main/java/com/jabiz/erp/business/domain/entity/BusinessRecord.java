package com.jabiz.erp.business.domain.entity;

import com.jabiz.erp.primitive.entity.PrimitiveEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


    @Builder
    public BusinessRecord(Long id,
                          String stateCode, String state,

                          String siteCode, String site, LocalDate timeStamp, String timeSlotCode, String timeSlot,
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
