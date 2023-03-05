package com.jabiz.erp.business.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.dashboard.domain.entity.Dashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BusinessRecordRepositoryCustom {

    Page<BusinessRecord> findWithSearchCriteria(BusinessRecordSearchCriteria searchCriteria);

    void updateBusinessState(BusinessRecord businessRecord);
    void updateBusinessRecord(BusinessRecord businessRecord);

    List<Dashboard> findBySiteIdsInGroupByBusinessState(List<Long> siteIds, LocalDate workAt);

}
