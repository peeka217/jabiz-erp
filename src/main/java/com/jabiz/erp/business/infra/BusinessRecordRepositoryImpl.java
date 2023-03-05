package com.jabiz.erp.business.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.constant.BusinessPartCode;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.jabiz.erp.dashboard.domain.entity.Dashboard;
import com.jabiz.erp.dashboard.domain.entity.QDashboard;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
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
    public List<Dashboard> findBySiteIdsInGroupByBusinessState(List<Long> siteIds, LocalDate workedAt) {
        List<Dashboard> dashboards = queryFactory
                .select(new QDashboard(
                        businessRecord.siteId,
                        businessRecord.siteName,
                        businessRecord.businessStateCode,
                        businessRecord.businessStateName,
                        ExpressionUtils.as(businessRecord.businessStateCode.count(), "recordCount")
                ))
                .from(businessRecord)
                .where(
                        this.inSiteIds(siteIds),
                        this.equalsWorkedAt(workedAt)
                )
                .groupBy(businessRecord.siteId, businessRecord.businessStateCode)
                .orderBy(businessRecord.id.desc())
                .fetch();

        return dashboards;
    }

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

    @Override
    public void updateBusinessState(BusinessRecord businessRecordEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, businessRecord);

        updateBuilder.set(businessRecord.businessStateCode, businessRecordEntity.getBusinessStateCode());
        updateBuilder.set(businessRecord.businessStateName, businessRecordEntity.getBusinessStateName());

        updateBuilder.set(businessRecord.updatedAt, businessRecordEntity.getUpdatedAt());
        updateBuilder.set(businessRecord.updatedBy, businessRecordEntity.getUpdatedBy());

        updateBuilder
                .where(
                        businessRecord.id.eq(businessRecordEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }

    @Override
    public void updateBusinessRecord(BusinessRecord businessRecordEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, businessRecord);

        if (businessRecordEntity.getBusinessPartCode() != null)
            updateBuilder.set(businessRecord.businessPartCode, businessRecordEntity.getBusinessPartCode());
        if (businessRecordEntity.getBusinessPartName() != null)
            updateBuilder.set(businessRecord.businessPartName, businessRecordEntity.getBusinessPartName());
        if (businessRecordEntity.getStartTime() != null)
            updateBuilder.set(businessRecord.startTime, businessRecordEntity.getStartTime());
        if (businessRecordEntity.getEndTime() != null)
            updateBuilder.set(businessRecord.endTime, businessRecordEntity.getEndTime());
        if (businessRecordEntity.getBasicTime() != null)
            updateBuilder.set(businessRecord.basicTime, businessRecordEntity.getBasicTime());
        if (businessRecordEntity.getOvertime() != null)
            updateBuilder.set(businessRecord.overtime, businessRecordEntity.getOvertime());

        if (businessRecordEntity.getBasicSalary() != null)
            updateBuilder.set(businessRecord.basicSalary, businessRecordEntity.getBasicSalary());
        if (businessRecordEntity.getExtraSalary() != null)
            updateBuilder.set(businessRecord.extraSalary, businessRecordEntity.getExtraSalary());
        if (businessRecordEntity.getCommission() != null)
            updateBuilder.set(businessRecord.commission, businessRecordEntity.getCommission());
        if (businessRecordEntity.getPaymentAmount() != null)
            updateBuilder.set(businessRecord.paymentAmount, businessRecordEntity.getPaymentAmount());

        if (businessRecordEntity.getWorkerId() != null)
            updateBuilder.set(businessRecord.workerId, businessRecordEntity.getWorkerId());
        if (businessRecordEntity.getWorkerRealName() != null)
            updateBuilder.set(businessRecord.workerRealName, businessRecordEntity.getWorkerRealName());
        if (businessRecordEntity.getResidentRegistrationNumber() != null)
            updateBuilder.set(businessRecord.residentRegistrationNumber, businessRecordEntity.getResidentRegistrationNumber());
        if (businessRecordEntity.getBankCode() != null)
            updateBuilder.set(businessRecord.bankCode, businessRecordEntity.getBankCode());
        if (businessRecordEntity.getBankName() != null)
            updateBuilder.set(businessRecord.bankName, businessRecordEntity.getBankName());
        if (businessRecordEntity.getAccountNumber() != null)
            updateBuilder.set(businessRecord.accountNumber, businessRecordEntity.getAccountNumber());
        if (businessRecordEntity.getAccountHolder() != null)
            updateBuilder.set(businessRecord.accountHolder, businessRecordEntity.getAccountHolder());

        if (businessRecordEntity.getNote() != null)
            updateBuilder.set(businessRecord.note, businessRecordEntity.getNote());

        updateBuilder.set(businessRecord.updatedAt, businessRecordEntity.getUpdatedAt());
        updateBuilder.set(businessRecord.updatedBy, businessRecordEntity.getUpdatedBy());

        updateBuilder
                .where(
                        businessRecord.id.eq(businessRecordEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }




    private BooleanExpression eqAgentId(Long agentId) {
        return agentId == null ? null : businessRecord.agentId.eq(agentId);
    }

    private BooleanExpression equalsSiteId(Long siteId) {
        return siteId == null ? null : businessRecord.siteId.eq(siteId);
    }

    private BooleanExpression inSiteIds(List<Long> siteIds) {
        return siteIds == null ? null : businessRecord.siteId.in(siteIds);
    }

    private BooleanExpression inBuisnessStatesCodes(List<BusinessStateCode> businessStateCodes) {
        return businessStateCodes == null ? null : businessRecord.businessStateCode.in(businessStateCodes);
    }

    private BooleanExpression betweenWorkedAt(LocalDate fromWorkedAt, LocalDate toWorkedAt) {
        return fromWorkedAt == null ? null : businessRecord.workedAt.between(fromWorkedAt, toWorkedAt);
    }

    private BooleanExpression equalsWorkedAt(LocalDate workedAt) {
        return workedAt == null ? null : businessRecord.workedAt.eq(workedAt);
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
