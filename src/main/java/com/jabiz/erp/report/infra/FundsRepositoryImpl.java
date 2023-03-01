package com.jabiz.erp.report.infra;

import com.jabiz.erp.report.domain.entity.Funds;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jabiz.erp.report.domain.entity.QFunds.funds;
import static com.jabiz.erp.report.domain.entity.QFundsRecord.fundsRecord;


@Repository
@RequiredArgsConstructor
public class FundsRepositoryImpl implements FundsRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public void updateFunds(Funds fundsEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, funds);

        updateBuilder.set(funds.lastFundsRecordId, fundsEntity.getLastFundsRecordId());
        updateBuilder.set(funds.balance, fundsEntity.getBalance());

        updateBuilder
                .where(
                        funds.id.eq(fundsEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }
}
