package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.controller.dto.FundingStatusSearchCriteria;
import com.jabiz.erp.report.domain.entity.FundsRecord;
import com.jabiz.erp.report.domain.entity.QFundsRecord;
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

import static com.jabiz.erp.report.domain.entity.QFundsRecord.fundsRecord;

@Repository
@RequiredArgsConstructor
public class FundsRecordRepositoryImpl implements FundsRecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Page<FundsRecord> findBySearchCriteria(FundingStatusSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(searchCriteria.getPagingNumber(), searchCriteria.getPagingSize());

        List<FundsRecord> paingFundsRecords = queryFactory
                .selectFrom(fundsRecord)
                .where(
                        this.equalCompanyId(searchCriteria.getCompanyId()),
                        this.equalTransactionTimestamp(searchCriteria.getTradedAt()),
                        this.lessOrEqual(searchCriteria.getLastFundsReocrdId())
                )
                .orderBy(fundsRecord.tradedAt.desc(), fundsRecord.fundsId.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(paingFundsRecords, pageable, paingFundsRecords.size());
    }

    @Override
    public void updateFundsRecord(FundsRecord fundsRecordEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, fundsRecord);

        if (fundsRecordEntity.getContents() != null)
            updateBuilder.set(fundsRecord.contents, fundsRecordEntity.getContents());
        if (fundsRecordEntity.getTransactionAmount() != null)
            updateBuilder.set(fundsRecord.transactionAmount, fundsRecordEntity.getTransactionAmount());

        updateBuilder.set(fundsRecord.updatedAt, fundsRecordEntity.getUpdatedAt());
        updateBuilder.set(fundsRecord.updatedBy, fundsRecordEntity.getUpdatedBy());

        updateBuilder
                .where(
                        fundsRecord.id.eq(fundsRecordEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }

    private BooleanExpression equalCompanyId(Long companyId) {
        return companyId == null ? null : fundsRecord.companyId.eq(companyId);
    }

    private BooleanExpression equalTransactionTimestamp(LocalDate tradedAt) {
        return tradedAt == null ? null : fundsRecord.tradedAt.eq(tradedAt);
    }

//    private BooleanExpression betweenTransactionTimestamp(LocalDate transactionTimestampFrom, LocalDate transactionTimestampTo) {
//        return transactionTimestampFrom == null ? null : fundsRecord.transactionTimestamp
//                .between(transactionTimestampFrom, transactionTimestampTo);
//    }

    private BooleanExpression lessOrEqual(Long lastFundsRecordId) {
        return lastFundsRecordId == null ? null : fundsRecord.id.loe(lastFundsRecordId);
    }
}
