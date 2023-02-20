package com.jabiz.erp.member.infra;

import com.jabiz.erp.member.domain.entity.Member;

public interface MemberRepositoryCustom {

    void updateMemberPassword(Member member);

}
