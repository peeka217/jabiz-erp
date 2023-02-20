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

    private String authority;

    private String accessible;

    private String email;
    private String passwordDigest;

    private String birthday;
    private String gender;
    private String marketingYn;

    @Builder
    public Member(Long id,
                  String signupCode,
                  String realName, String nickname, String email, String passwordDigest,
                  String authority,
                  String birthday, String gender,
                  String marketingYn) {
        this.id = id;
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
