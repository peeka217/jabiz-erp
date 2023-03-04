package com.jabiz.erp.business.service;

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
    public PagingResponse lookUpBusinessRecordsForRegistration(BusinessRecordSearchCriteria searchCriteria) {

        List<Long> temp = new ArrayList<>();
        temp.add(1L);
        temp.add(2L);
        List<Dashboard> temp2 = businessRecordRepository.findBySiteIdInGroupByBusinessState(temp);


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
    public PagingResponse lookUpBusinessRecordsForProcess(BusinessRecordSearchCriteria searchCriteria) {
        List<BusinessRecord> businessRecords = businessRecordRepository
                .findWithSearchCriteria(searchCriteria.getBusinessStateCodes() != null ? searchCriteria
                                : searchCriteria.searchLimitedByStateCodes(
                                Arrays.asList(BusinessStateCode.BS02, BusinessStateCode.BS03, BusinessStateCode.BS04)))
                .getContent();

        List<BusinessRecordResponse> businessRecordResponses = new ArrayList<>();
        businessRecords.forEach(businessRecord -> {
            businessRecordResponses.add(BusinessRecordResponse.of(businessRecord));
        });

        return PagingResponse.builder()
                .totalPagesCount(businessRecordResponses.size())
                .pages(Collections.singletonList(businessRecordResponses))
                .build();
    }

    @Transactional
    public void changeBusinessState(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            if (!BusinessStateCode.isChangeableState(request.getBusinessStateCode(), request.getNextBusinessStateCode()))
                throw new UnauthorizedAccessException();
            businessRecordRepository.updateBusinessState(request.toBusinessRecordForBusinessStateChange());
        });
    }


    @Transactional
    public PagingResponse registerBusinessRecord(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            businessRecordRepository.save(request.toBusinessRecord().setAgent());
        });

        return this.lookUpBusinessRecordsForRegistration(
                BusinessRecordSearchCriteria.builder()
                        .siteId(businessRecordRequests.get(0).getSiteId())
                        .pagingNumber(0)
                        .pagingSize(DEFAULT_PAGING_SIZE)
                        .build());

    }

    @Transactional
    public void editBusinessRecordsForRegistration(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            BusinessRecord businessRecord = request
                    .toBusinessRecord();

            if (!BusinessStateCode.isManipulableForRegistration(
                    businessRecordRepository.findById(request.getId())
                            .orElseThrow(ResourceNotFoundException::new)
                            .getBusinessStateCode()))
                throw new UnauthorizedAccessException();

            businessRecordRepository.updateBusinessRecord(businessRecord);
        });
    }

    @Transactional
    public void editBusinessRecordsForProcess(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            BusinessRecord businessRecord = request
                    .toBusinessRecord();

            businessRecordRepository.updateBusinessRecord(businessRecord);
        });
    }

    @Transactional
    public void deleteBusinessRecordForRegistration(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            if (!BusinessStateCode.isManipulableForRegistration(
                    businessRecordRepository.findById(request.getId())
                            .orElseThrow(ResourceNotFoundException::new)
                            .getBusinessStateCode()))
                throw new UnauthorizedAccessException();

            businessRecordRepository.delete(request.toBusinessRecord());
        });
    }

    @Transactional
    public void deleteBusinessRecordForProcess(List<BusinessRecordRequest> businessRecordRequests) {
        businessRecordRequests.forEach(request -> {
            businessRecordRepository.delete(request.toBusinessRecord());
        });
    }


}
