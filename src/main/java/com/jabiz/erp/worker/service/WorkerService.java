package com.jabiz.erp.worker.service;

import com.jabiz.erp.worker.controller.dto.WorkerRequest;
import com.jabiz.erp.worker.controller.dto.WorkerResponse;
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

    private static final int DEFAULT_PAGING_SIZE = 25;

    private final WorkerRepository workerRepository;

    public List<WorkerResponse> lookUpWorker(String nationalityCode, int pagingNumber , int pagingSize) {
        List<Worker> workers = workerRepository.findByNationalityCodeAndDeletedYn(nationalityCode, "N",
                PageRequest.of(pagingNumber, pagingSize, Sort.by(Sort.Order.desc("id")))).getContent();

        List<WorkerResponse> workerResponses = new ArrayList<>();
        workers.forEach(worker -> {
            workerResponses.add(WorkerResponse.of(worker));
        });

        return workerResponses;
    }

    @Transactional
    public List<WorkerResponse> saveWorker(String nationalityCode, List<WorkerRequest> workerRequests) {
        workerRequests.forEach(workerRequest -> {
            workerRepository.save(workerRequest.toWorker());
        });

        return this.lookUpWorker(nationalityCode, 0, DEFAULT_PAGING_SIZE);
    }

    @Transactional
    public void deleteWorker(List<WorkerRequest> workerRequests) {
        workerRequests.forEach(workerRequest -> {
            Worker worker = workerRequest.toWorker();
            worker.setDeletedYn("Y");
            workerRepository.save(worker);
        });
    }



}
