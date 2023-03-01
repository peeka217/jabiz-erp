package com.jabiz.erp.business.controller.dto;

import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
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

    private List<BusinessStateCode> businessStateCodes;
    private Long siteId;
    private LocalDate fromWorkedAt;
    private LocalDate toWorkedAt;
    private BusinessPartCode businessPartCode;
    private String accountNumber;
    private String workerRealName;

    private int pagingNumber;
    private int pagingSize;


    public BusinessRecordSearchCriteria searchLimitedByStateCodes(List<BusinessStateCode> businessStateCodes) {
        this.businessStateCodes = businessStateCodes;
        return this;
    }

    @Builder
    public BusinessRecordSearchCriteria(List<BusinessStateCode> businessStateCodes,
                                        Long siteId,
                                        LocalDate fromWorkedAt, LocalDate toWorkedAt, BusinessPartCode businessPartCode,
                                        String accountNumber, String workerRealName,
                                        int pagingNumber, int pagingSize) {
        this.businessStateCodes = businessStateCodes;
        this.siteId = siteId;
        this.fromWorkedAt = fromWorkedAt;
        this.toWorkedAt = toWorkedAt;
        this.businessPartCode = businessPartCode;
        this.accountNumber = accountNumber;
        this.workerRealName = workerRealName;

        this.pagingNumber = pagingNumber;
        this.pagingSize = pagingSize;
    }

}
