package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.controller.dto.FundingStatusSearchCriteria;
import com.jabiz.erp.report.domain.entity.FundsDailyRecord;
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

import static com.jabiz.erp.report.domain.entity.QFundsDailyRecord.fundsDailyRecord;
import static com.jabiz.erp.report.domain.entity.QFundsRecord.fundsRecord;

@Repository
@RequiredArgsConstructor
public class FundsDailyRecordRepositoryImpl implements FundsDailyRecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public List<FundsDailyRecord> findBySearchCriteria(FundingStatusSearchCriteria searchCriteria) {
        List<FundsDailyRecord> fundsDailyRecords = queryFactory
                .selectFrom(fundsDailyRecord)
                .where(
                        this.equalCompanyId(searchCriteria.getCompanyId()),
                        this.equalTransactionTimestamp(searchCriteria.getTradedAt())
                )
                .orderBy(fundsDailyRecord.companyId.asc(), fundsDailyRecord.fundsId.asc())
                .fetch();

        return fundsDailyRecords;
    }

    @Override
    public void updateFundsDailyRecord(FundsDailyRecord fundsDailyRecordEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, fundsDailyRecord);

        updateBuilder.set(fundsDailyRecord.depositAmount, fundsDailyRecordEntity.getDepositAmount());
        updateBuilder.set(fundsDailyRecord.withdrawalAmount, fundsDailyRecordEntity.getWithdrawalAmount());

        updateBuilder
                .where(
                        fundsDailyRecord.id.eq(fundsDailyRecordEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }

    private BooleanExpression equalCompanyId(Long companyId) {
        return companyId == null ? null : fundsDailyRecord.companyId.eq(companyId);
    }

    private BooleanExpression equalTransactionTimestamp(LocalDate tradedAt) {
        return tradedAt == null ? null : fundsDailyRecord.tradedAt.eq(tradedAt);
    }


}
