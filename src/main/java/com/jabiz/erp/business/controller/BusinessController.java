package com.jabiz.erp.business.controller;

import com.google.common.collect.ImmutableList;
import com.jabiz.erp.business.controller.dto.BusinessRecordRequest;
import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.service.BusinessService;
import com.jabiz.erp.primitive.entity.dto.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping("/business/registration/records")
    public ResponseEntity<PagingResponse> searchBusinessRecordsForRegistration(
            @RequestParam(value = "site_id", required = false) Long siteId,
            @RequestParam(value = "business_state_code", required = false) BusinessStateCode businessStateCode,
            @RequestParam(value = "from_worked_at", required = false) LocalDate fromWorkedAt,
            @RequestParam(value = "to_worked_at", required = false) LocalDate toWorkedAt,
            @RequestParam(value = "business_part_code", required = false) BusinessPartCode businessPartCode,
            @RequestParam(value = "worker_real_name", required = false) String workerRealName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(businessService.searchBusinessRecordsForRegistration(
                BusinessRecordSearchCriteria.builder()
                        .siteId(siteId)
                        .businessStateCodes(businessStateCode == null ? null :
                                ImmutableList.<BusinessStateCode>builder().add(businessStateCode).build())
                        .fromWorkedAt(fromWorkedAt)
                        .toWorkedAt(toWorkedAt)
                        .businessPartCode(businessPartCode)
                        .workerRealName(workerRealName)

                        .pagingNumber(pagingNumber)
                        .pagingSize(pagingSize)
                        .build()));
    }

    @GetMapping("/business/process/records")
    public ResponseEntity<PagingResponse> searchBusinessRecordsForProcess(
            @RequestParam(value = "site_id", required = false) Long siteId,
            @RequestParam(value = "business_state_code", required = false) BusinessStateCode businessStateCode,
            @RequestParam(value = "from_worked_at", required = false) LocalDate fromWorkedAt,
            @RequestParam(value = "to_worked_at", required = false) LocalDate toWorkedAt,
            @RequestParam(value = "account_number", required = false) String accountNumber,
            @RequestParam(value = "worker_real_name", required = false) String workerRealName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(businessService.searchBusinessRecordsForProcess(
                BusinessRecordSearchCriteria.builder()
                        .siteId(siteId)
                        .businessStateCodes(businessStateCode == null ? null :
                                ImmutableList.<BusinessStateCode>builder().add(businessStateCode).build())
                        .fromWorkedAt(fromWorkedAt)
                        .toWorkedAt(toWorkedAt)
                        .accountNumber(accountNumber)
                        .workerRealName(workerRealName)

                        .pagingNumber(pagingNumber)
                        .pagingSize(pagingSize)
                        .build()));
    }

    @PatchMapping(value = {"/business/registration/state", "/business/process/state"})
    public void changeBusinessState(@RequestBody List<BusinessRecordRequest> requests) {
        businessService.changeBusinessState(requests);
    }

    @PostMapping("/business/registration/records")
    public ResponseEntity<PagingResponse> registerBusinessRecords(@RequestBody List<BusinessRecordRequest> requests) {
        return ResponseEntity.ok(businessService.registerBusinessRecord(requests));
    }

    @PatchMapping("/business/registration/records")
    public void editBusinessRecordsForRegistration(@RequestBody List<BusinessRecordRequest> requests) {
        businessService.editBusinessRecordsForRegistration(requests);
    }

    @PatchMapping( "/business/process/records")
    public void editBusinessRecordsForProcess(@RequestBody List<BusinessRecordRequest> requests) {
        businessService.editBusinessRecordsForProcess(requests);
    }

    @DeleteMapping("/business/registration/records")
    public void deleteBusinessRecordForRegistration(@RequestBody List<BusinessRecordRequest> requests) {
        businessService.deleteBusinessRecordForRegistration(requests);
    }

    @DeleteMapping( "/business/process/records")
    public void deleteBusinessRecordForProcess(@RequestBody List<BusinessRecordRequest> requests) {
        businessService.deleteBusinessRecordForProcess(requests);
    }

}
