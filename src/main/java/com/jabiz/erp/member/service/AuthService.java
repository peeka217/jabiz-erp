package com.jabiz.erp.member.service;

import com.jabiz.erp.exception.InvalidDataException;
import com.jabiz.erp.member.controller.dto.AccessTokenRequest;
import com.jabiz.erp.member.controller.dto.MemberRequest;
import com.jabiz.erp.member.controller.dto.AccessToken;
import com.jabiz.erp.member.controller.dto.AccessTokenResponse;
import com.jabiz.erp.member.domain.entity.Member;
import com.jabiz.erp.member.entry.TokenProvider;
import com.jabiz.erp.member.infra.MemberRepository;
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
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;

    @Transactional
    public AccessTokenResponse signup(MemberRequest memberRequest) {
        Member member = memberRequest.toMember(passwordEncoder);
        member.setId(memberRepository.save(member).getId());

        AccessToken accessToken = new AccessToken();
        try {
            accessToken = tokenProvider.generateAccessToken(member, "ROLE_USER");
            accessToken.setId(member.getId());
            accessToken.setNickname(member.getNickname());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return AccessTokenResponse.of(accessToken, member);
    }

    @Transactional(readOnly = true)
    public AccessTokenResponse signin(MemberRequest memberRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequest.toAuthentication();

        Authentication authentication;
        AccessToken accessToken;
        Member member;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            member = memberRepository.findById(Long.parseLong(authentication.getName()))
                    .orElseThrow(() -> new InvalidDataException());
            accessToken = tokenProvider.generateAccessToken(member, member.getAccessible());
        } catch (BadCredentialsException e) {
            throw new InvalidDataException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return AccessTokenResponse.of(accessToken, member);
    }

}
