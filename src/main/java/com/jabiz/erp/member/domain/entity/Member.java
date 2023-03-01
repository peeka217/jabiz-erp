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
    private Long erpId;

    private String signupCode;
    private String memberStateCode;
    private String memberCode;

    private String authority;
    private String accessible;

    private String realName;
    private String nickname;
    private String profileImage;
    private String birthday;
    private String gender;

    private String email;
    private String passwordDigest;

    private String marketingYn;

    @Builder
    public Member(Long id, Long erpId,
                  String signupCode, String memberStateCode, String memberCode,
                  String authority, String accessible,
                  String realName, String nickname, String profileImage, String birthday, String gender,
                  String email, String passwordDigest,
                  String marketingYn) {
        this.id = id;
        this.erpId = erpId;

        this.signupCode = signupCode;
        this.memberStateCode = memberStateCode;
        this.memberCode = memberCode;

        this.authority = authority;
        this.accessible = accessible;

        this.realName = realName;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.birthday = birthday;
        this.gender = gender;

        this.email = email;
        this.passwordDigest = passwordDigest;

        this.marketingYn = marketingYn;
    }
}
