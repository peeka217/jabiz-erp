package com.jabiz.erp.member.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessToken {

    private Long id;
    private String realName;
    private String nickname;

    private String grantType;
    private String accessToken;
    private String refreshToken;

    private String accessTokenExpiresIn;

}
