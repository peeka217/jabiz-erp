package com.jabiz.erp.report.service;

import com.jabiz.erp.report.controller.dto.*;
import com.jabiz.erp.report.domain.entity.Funds;
import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
import com.jabiz.erp.report.domain.entity.FundsRecord;
import com.jabiz.erp.report.infra.FundsDailyRecordRepository;
import com.jabiz.erp.report.infra.FundsRecordRepository;
import com.jabiz.erp.report.infra.FundsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundsService {

    private static final int FUNDS_DEFAULT_PAGING_SIZE = 5;
    private static final int FUNDS_RECORD_DEFAULT_PAGING_SIZE = 15;
    private final FundsRepository fundsRepository;
    private final FundsDailyRecordRepository fundsDailyRecordRepository;
    private final FundsRecordRepository fundsRecordRepository;

    public FundingStatusResponse lookUpDailyFundingStatus(FundingStatusSearchCriteria searchCriteria) {
        List<FundsDailyRecordResponse> fundsDailyRecordResponses = new ArrayList<>();
        List<FundsDailyRecord> fundsDailyRecords = fundsDailyRecordRepository.findBySearchCriteria(searchCriteria);

        fundsDailyRecords.forEach(fundsDailyRecord -> {
            fundsDailyRecordResponses.add(FundsDailyRecordResponse.of(fundsDailyRecord));
        });

        List<FundsRecord> fundsRecords = fundsRecordRepository.findBySearchCriteria(searchCriteria).getContent();
        List<FundsRecordResponse> depositFundsResponses = new ArrayList<>();
        List<FundsRecordResponse> withdrawalFundsResponses = new ArrayList<>();
        fundsRecords.forEach(fundsRecord -> {
            if (fundsRecord.getTransactionCode().equals("AT00")) {
                depositFundsResponses.add(FundsRecordResponse.of(fundsRecord));

            } else if (fundsRecord.getTransactionCode().equals("AT01")) {
                withdrawalFundsResponses.add(FundsRecordResponse.of(fundsRecord));
            }
        });

        return FundingStatusResponse.builder()
                .fundsDailyRecords(fundsDailyRecordResponses)
                .fundsRecordsForDeposit(depositFundsResponses)
                .fundsRecordsForWithdrawal(withdrawalFundsResponses)
                .build();
    }

    @Transactional
    public FundingStatusResponse registerFundsRecords(List<FundsRecordRequest> fundsRecordRequests) {
        fundsRecordRequests.forEach(fundsRecordRequest -> {
            fundsRecordRepository.save(fundsRecordRequest.toFundsRecord());
        });

        return this.lookUpDailyFundingStatus(
                FundingStatusSearchCriteria.builder()
                .companyId(fundsRecordRequests.get(0).getCompanyId())
                .tradedAt(fundsRecordRequests.get(0).getTradedAt())
                .pagingSize(FUNDS_RECORD_DEFAULT_PAGING_SIZE)
                .pagingNumber(0)
                .build());
    }

    @Transactional
    public FundingStatusResponse editFundsRecords(List<FundsRecordRequest> fundsRecordRequests) {
        fundsRecordRequests.forEach(fundsRecordRequest -> {
            fundsRecordRepository.updateFundsRecord(fundsRecordRequest.toFundsRecord());
        });

        return this.lookUpDailyFundingStatus(
                FundingStatusSearchCriteria.builder()
                        .companyId(fundsRecordRequests.get(0).getCompanyId())
                        .ToTradedAt(fundsRecordRequests.get(0).getTradedAt())
                        .pagingSize(FUNDS_RECORD_DEFAULT_PAGING_SIZE)
                        .pagingNumber(0)
                        .build());
    }

    @Transactional
    public FundingStatusResponse deleteFundsRecords(List<FundsRecordRequest> fundsRecordRequests) {
        fundsRecordRequests.forEach(fundsRecordRequest -> {
            fundsRecordRepository.delete(fundsRecordRequest.toFundsRecord());
        });

        return this.lookUpDailyFundingStatus(
                FundingStatusSearchCriteria.builder()
                        .companyId(fundsRecordRequests.get(0).getCompanyId())
                        .tradedAt(fundsRecordRequests.get(0).getTradedAt())
                        .pagingSize(FUNDS_RECORD_DEFAULT_PAGING_SIZE)
                        .pagingNumber(0)
                        .build());
    }

}
