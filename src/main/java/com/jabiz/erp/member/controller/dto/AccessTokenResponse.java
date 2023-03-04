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

    private Long erpId;
    private String erpName;

    private String realName;
    private String nickname;

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiresIn;

    private String accessible;

    public static AccessTokenResponse of(AccessToken tokenDto, Member member) {
        return AccessTokenResponse.builder()
                .id(member.getId())

                .erpId(member.getErpId())
                .erpName(member.getErpName())

                .realName(member.getRealName())
                .nickname(member.getNickname())
                .grantType(tokenDto.getGrantType())
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .accessTokenExpiresIn(tokenDto.getRefreshToken())
                .accessible(member.getAccessible())
                .build();
    }

    @Builder
    public AccessTokenResponse(Long id,
                               Long erpId, String erpName,
                               String realName, String nickname,
                               String grantType, String accessToken, String refreshToken, String accessTokenExpiresIn,
                               String accessible) {
        this.id = id;

        this.erpId = erpId;
        this.erpName = erpName;

        this.realName = realName;
        this.nickname = nickname;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.accessible = accessible;
    }

}
