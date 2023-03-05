package com.jabiz.erp.worker.controller;

import com.jabiz.erp.worker.controller.dto.WorkerRequest;
import com.jabiz.erp.worker.controller.dto.WorkerResponse;
import com.jabiz.erp.worker.controller.dto.WorkerSearchCriteria;
import com.jabiz.erp.worker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/workers")
    public ResponseEntity<List<WorkerResponse>> searchWorkers(@RequestParam(value = "nationality_code", required = false) String nationalityCode,
                                                              @RequestParam(value = "real_name", required = false) String realName,
                                                              @RequestParam(value = "birthday", required = false) String birthday,
                                                              @RequestParam(value = "phone_number", required = false) String phoneNumber,
                                                              @RequestParam(value = "account_number", required = false) String accountNumber,
                                                              @RequestParam(value = "paging_number") int pagingNumber,
                                                              @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(workerService.searchWorkers(WorkerSearchCriteria.builder()
                .nationalityCode(nationalityCode)
                .realName(realName)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .accountNumber(accountNumber)
                .build(), pagingNumber, pagingSize));
    }

    @GetMapping("/workers/input")
    public ResponseEntity<List<WorkerResponse>> searchWorkersToInput(
            @RequestParam(value = "worker_id", required = false) Long workerId,
            @RequestParam(value = "worker_name") String workerName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(workerService.searchWorkersToInput(
                WorkerSearchCriteria.builder()
                        .id(workerId)
                        .realName(workerName)
                        .pagingNumber(pagingNumber)
                        .pagingSize(pagingSize)
                        .build()));
    }

    @PostMapping("/workers")
    public ResponseEntity<List<WorkerResponse>> registerWorkers(@RequestBody List<WorkerRequest> workerRequests) {
        return ResponseEntity.ok(workerService.saveWorkers(workerRequests));
    }

    @PatchMapping("/workers")
    public ResponseEntity<List<WorkerResponse>> editWorkers(@RequestBody List<WorkerRequest> workerRequests) {
        return ResponseEntity.ok(workerService.saveWorkers(workerRequests));
    }

    @DeleteMapping("/workers")
    public void deleteWorkers(@RequestBody List<WorkerRequest> workerRequests) {
        workerService.deleteWorkers(workerRequests);
    }

}
