package com.jabiz.erp.business.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BusinessRecordRepositoryCustom {

    Page<BusinessRecord> findWithSearchCriteria(BusinessRecordSearchCriteria searchCriteria, Pageable pageable);

    void updateBusinessRecordForAgent(BusinessRecord businessRecord);


}
