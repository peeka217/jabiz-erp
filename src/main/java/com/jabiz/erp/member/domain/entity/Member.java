package com.jabiz.erp.member.domain.entity;

import com.jabiz.erp.member.domain.constant.Authority;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String signupCode; // GN, GG,

    private String realName;
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Authority authority;

    private String email;
    private String passwordDigest;

    private String birthday;
    private String gender;
    private String marketingYn;

    @Builder
    public Member(String signupCode,
                  String realName, String nickname, String email, String passwordDigest,
                  Authority authority,
                  String birthday, String gender,
                  String marketingYn) {
        this.signupCode = signupCode;
        this.realName = realName;
        this.nickname = nickname;
        this.email = email;
        this.passwordDigest = passwordDigest;
        this.authority = authority;
        this.birthday = birthday;
        this.gender = gender;
        this.marketingYn = marketingYn;
    }
}
