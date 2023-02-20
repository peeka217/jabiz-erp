package com.jabiz.erp.member.service;

import com.jabiz.erp.exception.InvalidDataException;
import com.jabiz.erp.member.controller.dto.MemberRequest;
import com.jabiz.erp.member.domain.entity.Member;
import com.jabiz.erp.member.infra.MemberRepository;
import com.jabiz.erp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void changeMemberPassword(MemberRequest memberRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequest.toAuthentication();
        try {
            authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new InvalidDataException();
        }

        memberRepository.updateMemberPassword(Member.builder()
                .id(SecurityUtil.getMemberId())
                .passwordDigest(passwordEncoder.encode(memberRequest.getNewPassword()))
                .build());
    }


}
