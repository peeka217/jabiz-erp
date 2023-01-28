package com.jabiz.erp.worker.controller;

import com.jabiz.erp.worker.controller.dto.WorkerRequest;
import com.jabiz.erp.worker.controller.dto.WorkerResponse;
import com.jabiz.erp.worker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/worker")
    public ResponseEntity<List<WorkerResponse>> lookUpWorker(@RequestParam(value = "country_code") String countryCode,
                                                             @RequestParam(value = "paging_number") int pagingNumber,
                                                             @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(workerService.lookUpWorker(countryCode, pagingNumber, pagingSize));
    }

    @PostMapping("/worker")
    public ResponseEntity<List<WorkerResponse>> writeWorker(@RequestPart(value = "nationality_code") String nationalityCode,
                                                                               @RequestPart(value = "workers") List<WorkerRequest> workerRequests) {
        return ResponseEntity.ok(workerService.saveWorker(nationalityCode, workerRequests));
    }

    @PatchMapping("/worker")
    public ResponseEntity<List<WorkerResponse>> editWorker(@RequestPart(value = "nationality_code") String nationalityCode,
                                                                               @RequestPart(value = "workers") List<WorkerRequest> workerRequests) {
        return ResponseEntity.ok(workerService.saveWorker(nationalityCode, workerRequests));
    }

    @DeleteMapping("/worker")
    public void deleteWorker(@RequestBody List<WorkerRequest> workerRequests) {
        workerService.deleteWorker(workerRequests);
    }



}
