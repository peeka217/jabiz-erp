package com.jabiz.erp.business.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.jabiz.erp.business.domain.entity.QBusinessRecord.businessRecord;

@Repository
@RequiredArgsConstructor
public class BusinessRecordRepositoryImpl implements BusinessRecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Page<BusinessRecord> findWithSearchCriteria(BusinessRecordSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(searchCriteria.getPagingNumber(), searchCriteria.getPagingSize());

        List<BusinessRecord> businessRecords = queryFactory
                .selectFrom(businessRecord)
                .where(
                        this.equalsSiteId(searchCriteria.getSiteId()),
                        this.inBuisnessStatesCodes(searchCriteria.getBusinessStateCodes()),
                        this.betweenWorkedAt(searchCriteria.getFromWorkedAt(), searchCriteria.getToWorkedAt()),
                        this.equalsBbusinessPartCode(searchCriteria.getBusinessPartCode()),
                        this.containsAccountNumber(searchCriteria.getAccountNumber()),
                        this.containsWorkerRealName(searchCriteria.getWorkerRealName())
                )
                .orderBy(businessRecord.id.desc(), businessRecord.businessStateCode.asc(), businessRecord.siteId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(businessRecords, pageable, businessRecords.size());

    }

//    @Override
//    public void updateBusinessRecordForAgent(BusinessRecord businessRecordEntity) {
//        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, businessRecord);
//
//        if (businessRecordEntity.getSiteCode() != null)
//            updateBuilder.set(businessRecord.siteCode, businessRecordEntity.getSiteCode());
//        if (businessRecordEntity.getSite() != null)
//            updateBuilder.set(businessRecord.site, businessRecordEntity.getSite());
//        if (businessRecordEntity.getTimeStamp() != null)
//            updateBuilder.set(businessRecord.timeStamp, businessRecordEntity.getTimeStamp());
//        if (businessRecordEntity.getTimeSlotCode() != null)
//            updateBuilder.set(businessRecord.timeSlotCode, businessRecordEntity.getTimeSlotCode());
//        if (businessRecordEntity.getTimeSlot() != null)
//            updateBuilder.set(businessRecord.timeSlot, businessRecordEntity.getTimeSlot());
//
//        if (businessRecordEntity.getRealName() != null)
//            updateBuilder.set(businessRecord.realName, businessRecordEntity.getRealName());
//        if (businessRecordEntity.getResidentRegistrationNumber() != null)
//            updateBuilder.set(businessRecord.residentRegistrationNumber, businessRecordEntity.getResidentRegistrationNumber());
//
//        updateBuilder
//                .where(
//                        businessRecord.id.eq(businessRecordEntity.getId())
//                ).execute();
//
//        em.clear();
//        em.flush();
//    }


    private BooleanExpression eqAgentId(Long agentId) {
        return agentId == null ? null : businessRecord.agentId.eq(agentId);
    }

    private BooleanExpression equalsSiteId(Long siteId) {
        return siteId == null ? null : businessRecord.siteId.eq(siteId);
    }

    private BooleanExpression inBuisnessStatesCodes(List<BusinessStateCode> businessStateCodes) {
        return businessStateCodes == null ? null : businessRecord.businessStateCode.in(businessStateCodes);
    }

    private BooleanExpression betweenWorkedAt(LocalDate fromWorkedAt, LocalDate toWorkedAt) {
        if (fromWorkedAt == null)
            return null;
        return businessRecord.workedAt.between(fromWorkedAt, toWorkedAt);
    }

    private BooleanExpression equalsBbusinessPartCode(BusinessPartCode businessPartCode) {
        return businessPartCode == null ? null : businessRecord.businessPartCode.eq(businessPartCode);
    }

    private BooleanExpression containsAccountNumber(String accountNumber) {
        return accountNumber == null ? null : businessRecord.accountNumber.contains(accountNumber);
    }

    private BooleanExpression containsWorkerRealName(String workerRealName){
        return workerRealName == null ? null : businessRecord.workerRealName.contains(workerRealName);
    }

}
