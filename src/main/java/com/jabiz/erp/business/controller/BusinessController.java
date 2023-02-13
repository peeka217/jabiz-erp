package com.jabiz.erp.business.controller;

import com.jabiz.erp.business.controller.dto.BusinessRecordRequest;
import com.jabiz.erp.business.controller.dto.BusinessRecordResponse;
import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.service.BusinessService;
import com.jabiz.erp.worker.controller.dto.WorkerRequest;
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

    @GetMapping("/business/records/agent")
    public ResponseEntity<List<BusinessRecordResponse>> lookUpBusinessRecordsForAgent(
            @RequestParam(value = "site_code", required = false) String siteCode,
            @RequestParam(value = "state_code", required = false) String stateCode,
            @RequestParam(value = "time_stamp_from", required = false) LocalDate timeStampFrom,
            @RequestParam(value = "time_stamp_to", required = false) LocalDate timeStampTo,
            @RequestParam(value = "time_slot_code", required = false) String timeSlotCode,
            @RequestParam(value = "real_name", required = false) String realName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(businessService.lookUpBusinessRecordsForAgent(
                BusinessRecordSearchCriteria.builder()
                        .siteCode(siteCode)
                        .stateCodes(stateCode == null ? null : Arrays.asList(stateCode))
                        .timeStampFrom(timeStampFrom)
                        .timeStampTo(timeStampTo)
                        .timeSlotCode(timeSlotCode)
                        .realName(realName)
                        .build(), pagingNumber, pagingSize));
    }

    @GetMapping("/business/records/approver")
    public ResponseEntity<List<BusinessRecordResponse>> lookUpBusinessRecordsForApprover(
            @RequestParam(value = "site_code", required = false) String siteCode,
            @RequestParam(value = "state_code", required = false) String stateCode,
            @RequestParam(value = "time_stamp_from", required = false) LocalDate timeStampFrom,
            @RequestParam(value = "time_stamp_to", required = false) LocalDate timeStampTo,
            @RequestParam(value = "account_number", required = false) String accountNumber,
            @RequestParam(value = "real_name", required = false) String realName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(businessService.lookUpBusinessRecordsForApprover(
                BusinessRecordSearchCriteria.builder()
                        .siteCode(siteCode)
                        .stateCodes(stateCode == null ? null : Arrays.asList(stateCode))
                        .timeStampFrom(timeStampFrom)
                        .timeStampTo(timeStampTo)
                        .accountNumber(accountNumber)
                        .realName(realName)
                        .build(), pagingNumber, pagingSize));
    }

    @PostMapping("/business/records")
    public ResponseEntity<List<BusinessRecordResponse>> registerBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
        return ResponseEntity.ok(businessService.registerBusinessRecord(businessRecordRequests));
    }

    @PatchMapping("/business/records/agent")
    public void editBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
        businessService.editBusinessRecords(businessRecordRequests);
    }

    @DeleteMapping("/business/records/agent")
    public void deleteBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
        businessService.deleteBusinessRecord(businessRecordRequests);
    }

}
