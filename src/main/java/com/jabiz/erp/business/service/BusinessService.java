package com.jabiz.erp.business.service;

import com.jabiz.erp.business.controller.dto.BusinessRecordRequest;
import com.jabiz.erp.business.controller.dto.BusinessRecordResponse;
import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.business.infra.BusinessRecordRepository;
import com.jabiz.erp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private static final int DEFAULT_PAGING_SIZE = 15;

    private final BusinessRecordRepository businessRecordRepository;

    public List<BusinessRecordResponse> lookUpBusinessRecordsForAgent(BusinessRecordSearchCriteria searchCriteria,
                                                                      int pagingNumber, int pagingSize) {
        List<BusinessRecord> businessRecords = businessRecordRepository
                .findWithSearchCriteria(searchCriteria.searchLimitedByAgentSession(),
                        PageRequest.of(pagingNumber, pagingSize)).getContent();

        List<BusinessRecordResponse> businessRecordResponses = new ArrayList<>();
        businessRecords.forEach(businessRecord -> {
            businessRecordResponses.add(BusinessRecordResponse.of(businessRecord));
        });

        return businessRecordResponses;
    }

    public List<BusinessRecordResponse> lookUpBusinessRecordsForApprover(BusinessRecordSearchCriteria searchCriteria,
                                                                         int pagingNumber, int pagingSize) {
        List<BusinessRecord> businessRecords = businessRecordRepository
                .findWithSearchCriteria(searchCriteria.getStateCodes() == null
                                ? searchCriteria.searchLimitedByStateCodes(Arrays.asList("BS01", "BS02")) : searchCriteria,
                        PageRequest.of(pagingNumber, pagingSize)).getContent();

        List<BusinessRecordResponse> businessRecordResponses = new ArrayList<>();
        businessRecords.forEach(businessRecord -> {
            businessRecordResponses.add(BusinessRecordResponse.of(businessRecord));
        });

        return businessRecordResponses;
    }

    public List<BusinessRecordResponse> registerBusinessRecord(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(businessRecordRequest -> {
            BusinessRecord businessRecord = businessRecordRequest
                    .toBusinessRecordForRegistration(businessRecordRequest.toBusinessRecord());
            businessRecordRepository.save(businessRecord);
        });

        return this.lookUpBusinessRecordsForAgent(
                BusinessRecordSearchCriteria.builder()
                        .siteCode(businessRecordRequests.get(0).getSiteCode()).build(),
                0, DEFAULT_PAGING_SIZE);
    }

    @Transactional
    public void editBusinessRecords(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(businessRecordRequest -> {
            BusinessRecord businessRecord = businessRecordRequest.toBusinessRecord();
            businessRecord.setUpdatedBy(SecurityUtil.getMemberId());
            businessRecordRepository.save(businessRecord);
        });
    }

    public void deleteBusinessRecord(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(businessRecordRequest -> {
            businessRecordRepository.delete(businessRecordRequest.toBusinessRecord());
        });
    }


}
