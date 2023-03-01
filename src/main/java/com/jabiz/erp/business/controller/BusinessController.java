package com.jabiz.erp.business.controller;

import com.jabiz.erp.business.controller.dto.BusinessRecordRequest;
import com.jabiz.erp.business.controller.dto.BusinessRecordResponse;
import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
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

    @GetMapping("/business/registration/records")
    public ResponseEntity<List<BusinessRecordResponse>> lookUpBusinessRecordsForRegistration(
            @RequestParam(value = "site_id", required = false) Long siteId,
            @RequestParam(value = "business_state_code", required = false) BusinessStateCode businessStateCode,
            @RequestParam(value = "from_worked_at", required = false) LocalDate fromWorkedAt,
            @RequestParam(value = "to_worked_at", required = false) LocalDate toWorkedAt,
            @RequestParam(value = "business_part_code", required = false) BusinessPartCode businessPartCode,
            @RequestParam(value = "worker_real_name", required = false) String workerRealName,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(businessService.lookUpBusinessRecordsForRegistration(
                BusinessRecordSearchCriteria.builder()
                        .siteId(siteId)
                        .businessStateCodes(businessStateCode == null ? null : Arrays.asList(businessStateCode))
                        .fromWorkedAt(fromWorkedAt)
                        .toWorkedAt(toWorkedAt)
                        .businessPartCode(businessPartCode)
                        .workerRealName(workerRealName)

                        .pagingNumber(pagingNumber)
                        .pagingSize(pagingSize)
                        .build()));
    }

//    @GetMapping("/business/process/records")
//    public ResponseEntity<List<BusinessRecordResponse>> lookUpBusinessRecordsForProcess(
//            @RequestParam(value = "site_code", required = false) String siteCode,
//            @RequestParam(value = "state_code", required = false) String stateCode,
//            @RequestParam(value = "time_stamp_from", required = false) LocalDate timeStampFrom,
//            @RequestParam(value = "time_stamp_to", required = false) LocalDate timeStampTo,
//            @RequestParam(value = "account_number", required = false) String accountNumber,
//            @RequestParam(value = "real_name", required = false) String realName,
//            @RequestParam(value = "paging_number") int pagingNumber,
//            @RequestParam(value = "paging_size") int pagingSize) {
//        return ResponseEntity.ok(businessService.lookUpBusinessRecordsForApprover(
//                BusinessRecordSearchCriteria.builder()
//                        .siteCode(siteCode)
//                        .stateCodes(stateCode == null ? null : Arrays.asList(stateCode))
//                        .timeStampFrom(timeStampFrom)
//                        .timeStampTo(timeStampTo)
//                        .accountNumber(accountNumber)
//                        .realName(realName)
//                        .build(), pagingNumber, pagingSize));
//    }
//
//    @PostMapping("/business/registration/records")
//    public ResponseEntity<List<BusinessRecordResponse>> registerBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
//        return ResponseEntity.ok(businessService.registerBusinessRecord(businessRecordRequests));
//    }
//
//    @PatchMapping("/business/registration/records")
//    public void editBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
//        businessService.editBusinessRecords(businessRecordRequests);
//    }
//
//    @DeleteMapping("/business/registration/records")
//    public void deleteBusinessRecords(@RequestBody List<BusinessRecordRequest> businessRecordRequests) {
//        businessService.deleteBusinessRecord(businessRecordRequests);
//    }

}
