package com.jabiz.erp.worker.infra;

import com.jabiz.erp.worker.controller.dto.WorkerSearchCriteria;
import com.jabiz.erp.worker.domain.entity.Worker;
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

import java.util.List;

import static com.jabiz.erp.business.domain.entity.QBusinessRecord.businessRecord;
import static com.jabiz.erp.worker.domain.entity.QWorker.worker;

@Repository
@RequiredArgsConstructor
public class WorkerRepositoryImpl implements WorkerRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;


    @Override
    public Page<Worker> findWithSearchCriteria(WorkerSearchCriteria searchCriteria, Pageable pageable) {
        List<Worker> workers = queryFactory
                .selectFrom(worker)
                .where(
                        this.containsRealName(searchCriteria.getRealName()),
                        this.containsBirthday(searchCriteria.getBirthday())
                )
                .orderBy(worker.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(workers, pageable, workers.size());
    }

    @Override
    public void updateWorkerState(Worker workerEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, worker);
        updateBuilder.set(worker.stateCode, workerEntity.getStateCode());

        updateBuilder
                .where(
                        worker.id.eq(workerEntity.getId())
                )
                .execute();

        em.clear();
        em.flush();
    }

    private BooleanExpression containsRealName(String realName){
        return realName == null ? null : worker.realName.contains(realName);
    }

    private BooleanExpression containsBirthday(String birthday){
        return birthday == null ? null : worker.birthday.contains(birthday);
    }
}
