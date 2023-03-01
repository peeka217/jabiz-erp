package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.controller.dto.FundingStatusSearchCriteria;
import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FundsDailyRecordRepositoryCustom {

    List<FundsDailyRecord> findBySearchCriteria(FundingStatusSearchCriteria searchCriteria);

    void updateFundsDailyRecord(FundsDailyRecord fundsDailyRecord);

}
