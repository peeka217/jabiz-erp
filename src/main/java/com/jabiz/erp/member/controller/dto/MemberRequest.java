package com.jabiz.erp.member.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jabiz.erp.member.domain.constant.Authority;
import com.jabiz.erp.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberRequest {

    private String signupCode;

    private String realName;
    private String nickname;
    private String email;
    private String password;
    private String birthday;
    private String gender;

    private String marketingYn;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .signupCode("GN")
                .nickname(this.nickname)
                .email(this.email)
                .passwordDigest(passwordEncoder.encode(this.password))
                .authority(Authority.ROLE_USER)
                .birthday(this.birthday)
                .gender(this.gender)
                .marketingYn(this.marketingYn)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
