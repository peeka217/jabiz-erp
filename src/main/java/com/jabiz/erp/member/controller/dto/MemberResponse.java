package com.jabiz.erp.member.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberResponse {

    private Long id;
    private String nickname;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .build();
    }

    @Builder
    public MemberResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

}
