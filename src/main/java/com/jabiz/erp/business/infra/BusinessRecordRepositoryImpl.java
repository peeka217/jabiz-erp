package com.jabiz.erp.business.infra;

import com.jabiz.erp.business.controller.dto.BusinessRecordSearchCriteria;
import com.jabiz.erp.business.domain.entity.BusinessRecord;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<BusinessRecord> findWithSearchCriteria(BusinessRecordSearchCriteria searchCriteria, Pageable pageable) {
        List<BusinessRecord> businessRecords = queryFactory
                .selectFrom(businessRecord)
                .where(
                        this.eqAgentId(searchCriteria.getAgentId()),
                        this.eqSiteCode(searchCriteria.getSiteCode()),
                        this.inStatesCodes(searchCriteria.getStateCodes()),
                        this.betweenTimeStamp(searchCriteria.getTimeStampFrom(), searchCriteria.getTimeStampTo()),
                        this.eqTimeSlotCode(searchCriteria.getTimeSlotCode()),
                        this.containsAccountNumber(searchCriteria.getAccountNumber()),
                        this.containsRealName(searchCriteria.getRealName())
                )
                .orderBy(businessRecord.id.desc(), businessRecord.stateCode.asc(), businessRecord.siteCode.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(businessRecords, pageable, businessRecords.size());

    }

    @Override
    public void updateBusinessRecordForAgent(BusinessRecord businessRecordEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, businessRecord);

        if (businessRecordEntity.getSiteCode() != null)
            updateBuilder.set(businessRecord.siteCode, businessRecordEntity.getSiteCode());
        if (businessRecordEntity.getSite() != null)
            updateBuilder.set(businessRecord.site, businessRecordEntity.getSite());
        if (businessRecordEntity.getTimeStamp() != null)
            updateBuilder.set(businessRecord.timeStamp, businessRecordEntity.getTimeStamp());
        if (businessRecordEntity.getTimeSlotCode() != null)
            updateBuilder.set(businessRecord.timeSlotCode, businessRecordEntity.getTimeSlotCode());
        if (businessRecordEntity.getTimeSlot() != null)
            updateBuilder.set(businessRecord.timeSlot, businessRecordEntity.getTimeSlot());

        if (businessRecordEntity.getRealName() != null)
            updateBuilder.set(businessRecord.realName, businessRecordEntity.getRealName());
        if (businessRecordEntity.getResidentRegistrationNumber() != null)
            updateBuilder.set(businessRecord.residentRegistrationNumber, businessRecordEntity.getResidentRegistrationNumber());

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

    private BooleanExpression eqSiteCode(String siteCode) {
        return siteCode == null ? null : businessRecord.siteCode.eq(siteCode);
    }

    private BooleanExpression inStatesCodes(List<String> stateCodes) {
        return stateCodes == null ? null : businessRecord.stateCode.in(stateCodes);
    }

    private BooleanExpression betweenTimeStamp(LocalDate timeStampFrom, LocalDate timeStampTo) {
        if (timeStampFrom == null)
            return null;
        return businessRecord.timeStamp.between(timeStampFrom, timeStampTo);
    }

    private BooleanExpression eqTimeSlotCode(String timeSlotCode) {
        return timeSlotCode == null ? null : businessRecord.timeSlotCode.eq(timeSlotCode);
    }

    private BooleanExpression containsAccountNumber(String accountNumber) {
        return accountNumber == null ? null : businessRecord.accountNumber.contains(accountNumber);
    }

    private BooleanExpression containsRealName(String realName){
        return realName == null ? null : businessRecord.realName.contains(realName);
    }



}
