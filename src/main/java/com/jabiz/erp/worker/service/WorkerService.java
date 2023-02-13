package com.jabiz.erp.worker.service;

import com.jabiz.erp.primitive.entity.constant.StateCode;
import com.jabiz.erp.util.SecurityUtil;
import com.jabiz.erp.worker.controller.dto.WorkerRequest;
import com.jabiz.erp.worker.controller.dto.WorkerResponse;
import com.jabiz.erp.worker.controller.dto.WorkerSearchCriteria;
import com.jabiz.erp.worker.domain.entity.Worker;
import com.jabiz.erp.worker.infra.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private static final int DEFAULT_PAGING_SIZE = 15;

    private final WorkerRepository workerRepository;

    public List<WorkerResponse> lookUpWorkers(WorkerSearchCriteria searchCriteria, int pagingNumber , int pagingSize) {
        List<Worker> workers = workerRepository.findWithSearchCriteria(searchCriteria,
                PageRequest.of(pagingNumber, pagingSize)).getContent();

        List<WorkerResponse> workerResponses = new ArrayList<>();
        workers.forEach(worker -> {
            workerResponses.add(WorkerResponse.of(worker));
        });

        return workerResponses;
    }

    @Transactional
    public List<WorkerResponse> saveWorkers(List<WorkerRequest> workerRequests) {
        workerRequests.forEach(workerRequest -> {
            workerRepository.save(workerRequest.toWorkerForRegistration());
        });

        return this.lookUpWorkers(WorkerSearchCriteria.builder()
                .nationalityCode(workerRequests.get(0).getNationalityCode()).build(),
                0, DEFAULT_PAGING_SIZE);
    }

    @Transactional
    public void deleteWorkers(List<WorkerRequest> workerRequests) {
        workerRequests.forEach(workerRequest -> {
            workerRepository.updateWorkerState(workerRequest.toWorkerForState("WO"));
        });
    }

}
