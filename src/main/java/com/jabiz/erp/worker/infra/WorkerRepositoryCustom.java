package com.jabiz.erp.worker.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.worker.controller.dto.WorkerSearchCriteria;
import com.jabiz.erp.worker.domain.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkerRepositoryCustom {

    Page<Worker> findWithSearchCriteria(WorkerSearchCriteria searchCriteria, Pageable pageable);

    void updateWorkerState(Worker worker);
}
