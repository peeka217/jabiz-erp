package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.controller.dto.FundingStatusSearchCriteria;
import com.jabiz.erp.report.domain.entity.FundsRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundsRecordRepositoryCustom {

    Page<FundsRecord> findBySearchCriteria(FundingStatusSearchCriteria searchCriteria);

    void updateFundsRecord(FundsRecord fundsRecord);
}
