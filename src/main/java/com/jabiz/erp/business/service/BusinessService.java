package com.jabiz.erp.business.service;

import com.google.common.collect.ImmutableList;
import com.jabiz.erp.business.controller.dto.BusinessRecordRequest;
import com.jabiz.erp.business.controller.dto.BusinessRecordResponse;
import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.business.infra.BusinessRecordRepository;
import com.jabiz.erp.dashboard.domain.entity.Dashboard;
import com.jabiz.erp.exception.ResourceNotFoundException;
import com.jabiz.erp.exception.StateConflictException;
import com.jabiz.erp.exception.UnauthorizedAccessException;
import com.jabiz.erp.primitive.entity.dto.PagingResponse;
import com.jabiz.erp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private static final int DEFAULT_PAGING_SIZE = 15;

    private final BusinessRecordRepository businessRecordRepository;

    @Transactional(readOnly = true)
    public PagingResponse searchBusinessRecordsForRegistration(BusinessRecordSearchCriteria searchCriteria) {
        List<BusinessRecord> businessRecords = businessRecordRepository
                .findWithSearchCriteria(searchCriteria)
                .getContent();

        List<BusinessRecordResponse> businessRecordResponses = new ArrayList<>();
        businessRecords.forEach(businessRecord -> {
            businessRecordResponses.add(BusinessRecordResponse.of(businessRecord));
        });

        return PagingResponse.builder()
                .totalPagesCount(businessRecordResponses.size())
                .pages(businessRecordResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public PagingResponse searchBusinessRecordsForProcess(BusinessRecordSearchCriteria searchCriteria) {
        List<BusinessRecord> businessRecords = businessRecordRepository
                .findWithSearchCriteria(searchCriteria.getBusinessStateCodes() != null ? searchCriteria
                        : searchCriteria.searchLimitedByStateCodes(
                        ImmutableList.<BusinessStateCode>builder()
                                .add(BusinessStateCode.BS02)
                                .add(BusinessStateCode.BS03)
                                .add(BusinessStateCode.BS04)
                                .build()))
                .getContent();

        List<BusinessRecordResponse> businessRecordResponses = new ArrayList<>();
        businessRecords.forEach(businessRecord -> {
            businessRecordResponses.add(BusinessRecordResponse.of(businessRecord));
        });

        return PagingResponse.builder()
                .totalPagesCount(businessRecordResponses.size())
                .pages(businessRecordResponses)
                .build();
    }

    @Transactional
    public void changeBusinessState(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            if (!BusinessStateCode.isChangeableState(request.getBusinessStateCode(), request.getNextBusinessStateCode()))
                throw new UnauthorizedAccessException();
            businessRecordRepository.updateBusinessState(request.toBusinessRecordForBusinessStateChange());
        });
    }


    @Transactional
    public PagingResponse registerBusinessRecord(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            businessRecordRepository.save(request.toBusinessRecord().setAgent());
        });

        return this.searchBusinessRecordsForRegistration(
                BusinessRecordSearchCriteria.builder()
                        .siteId(requests.get(0).getSiteId())
                        .pagingNumber(0)
                        .pagingSize(DEFAULT_PAGING_SIZE)
                        .build());

    }

    @Transactional
    public void editBusinessRecordsForRegistration(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            BusinessRecord businessRecord = request
                    .toBusinessRecord();

            if (!BusinessStateCode.isManipulableForRegistration(
                    businessRecordRepository.findById(request.getBusinessRecordId())
                            .orElseThrow(ResourceNotFoundException::new)
                            .getBusinessStateCode()))
                throw new UnauthorizedAccessException();

            businessRecordRepository.updateBusinessRecord(businessRecord);
        });
    }

    @Transactional
    public void editBusinessRecordsForProcess(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            BusinessRecord businessRecord = request
                    .toBusinessRecord();

            businessRecordRepository.updateBusinessRecord(businessRecord);
        });
    }

    @Transactional
    public void deleteBusinessRecordForRegistration(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            if (!BusinessStateCode.isManipulableForRegistration(
                    businessRecordRepository.findById(request.getBusinessRecordId())
                            .orElseThrow(ResourceNotFoundException::new)
                            .getBusinessStateCode()))
                throw new UnauthorizedAccessException();

            businessRecordRepository.delete(request.toBusinessRecord());
        });
    }

    @Transactional
    public void deleteBusinessRecordForProcess(List<BusinessRecordRequest> requests) {
        requests.forEach(request -> {
            businessRecordRepository.delete(request.toBusinessRecord());
        });
    }


}
