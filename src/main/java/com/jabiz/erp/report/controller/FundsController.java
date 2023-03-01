package com.jabiz.erp.report.controller;


import com.jabiz.erp.report.controller.dto.FundingStatusResponse;
import com.jabiz.erp.report.controller.dto.FundingStatusSearchCriteria;
import com.jabiz.erp.report.controller.dto.FundsRecordRequest;
import com.jabiz.erp.report.service.FundsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FundsController {

    private final FundsService fundsService;

    @GetMapping("/report/funds/status")
    public ResponseEntity<FundingStatusResponse> lookUpDailyFundingStatus(
            @RequestParam(value = "company_id", required = false) Long companyId,
            @RequestParam(value = "transaction_timestamp") LocalDate transactionTimestamp,
            @RequestParam(value = "paging_number") int pagingNumber,
            @RequestParam(value = "paging_size") int pagingSize) {
        return ResponseEntity.ok(fundsService.lookUpDailyFundingStatus(
                FundingStatusSearchCriteria.builder()
                        .companyId(companyId)
                        .tradedAt(transactionTimestamp)
                        .pagingNumber(pagingNumber)
                        .pagingSize(pagingSize)
                        .build()));
    }

    @PostMapping("/report/funds/records")
    public ResponseEntity<FundingStatusResponse> registerFundsRecords(@RequestBody List<FundsRecordRequest> fundsRecordRequests) {
        return ResponseEntity.ok(fundsService.registerFundsRecords(fundsRecordRequests));
    }

    @PatchMapping("/report/funds/records")
    public ResponseEntity<FundingStatusResponse> editFundsRecords(@RequestBody List<FundsRecordRequest> fundsRecordRequests) {
        return ResponseEntity.ok(fundsService.editFundsRecords(fundsRecordRequests));
    }

    @DeleteMapping("/report/funds/records")
    public ResponseEntity<FundingStatusResponse> deleteFundsRecords(@RequestBody List<FundsRecordRequest> fundsRecordRequests) {
        return ResponseEntity.ok(fundsService.deleteFundsRecords(fundsRecordRequests));
    }

}
