package com.jabiz.erp.business.controller.dto;

import com.jabiz.erp.util.SecurityUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class BusinessRecordSearchCriteria {


    private Long agentId;
    private List<String> stateCodes;
    private String siteCode;
    private LocalDate timeStampFrom;
    private LocalDate timeStampTo;
    private String timeSlotCode;
    private String accountNumber;
    private String realName;

    private int pagingNumber;
    private int pagingSize;

    public BusinessRecordSearchCriteria searchLimitedByAgentSession() {
        this.agentId = SecurityUtil.getMemberId();
        return this;
    }

    public BusinessRecordSearchCriteria searchLimitedByStateCodes(List<String> stateCodes) {
        this.stateCodes = stateCodes;
        return this;
    }

    @Builder
    public BusinessRecordSearchCriteria(Long agentId, List<String> stateCodes, String siteCode,
                                        LocalDate timeStampFrom, LocalDate timeStampTo, String timeSlotCode,
                                        String accountNumber, String realName,
                                        int pagingNumber, int pagingSize) {
        this.agentId = agentId;
        this.stateCodes = stateCodes;
        this.siteCode = siteCode;
        this.timeStampFrom = timeStampFrom;
        this.timeStampTo = timeStampTo;
        this.timeSlotCode = timeSlotCode;
        this.accountNumber = accountNumber;
        this.realName = realName;
        this.pagingNumber = pagingNumber;
        this.pagingSize = pagingSize;
    }

}
