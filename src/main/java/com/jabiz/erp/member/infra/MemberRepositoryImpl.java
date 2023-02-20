package com.jabiz.erp.member.infra;

import com.jabiz.erp.member.domain.entity.Member;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jabiz.erp.business.domain.entity.QBusinessRecord.businessRecord;
import static com.jabiz.erp.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public void updateMemberPassword(Member memberEntity) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, member);

        updateBuilder.set(member.passwordDigest, memberEntity.getPasswordDigest());

        updateBuilder
                .where(
                        member.id.eq(memberEntity.getId())
                ).execute();

        em.clear();
        em.flush();
    }
}
