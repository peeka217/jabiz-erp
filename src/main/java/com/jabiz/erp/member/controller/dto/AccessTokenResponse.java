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
public class AccessTokenResponse {

    private Long id;
    private String realName;
    private String nickname;

    private String grantType;
    private String accessToken;
    private String refreshToken;

    private String accessTokenExpiresIn;

    public static AccessTokenResponse of(AccessToken tokenDto, Member member) {
        return AccessTokenResponse.builder()
                .id(member.getId())
                .realName(member.getRealName())
                .nickname(member.getNickname())
                .grantType(tokenDto.getGrantType())
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .accessTokenExpiresIn(tokenDto.getRefreshToken())
                .build();
    }

    @Builder
    public AccessTokenResponse(Long id,
                               String realName, String nickname,
                               String grantType, String accessToken, String refreshToken, String accessTokenExpiresIn) {
        this.id = id;
        this.realName = realName;
        this.nickname = nickname;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

}
