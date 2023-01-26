package com.jabiz.erp.member.service;

import com.jabiz.erp.exception.NotFoundException;
import com.jabiz.erp.exception.UnauthenticatedAccessException;
import com.jabiz.erp.member.controller.dto.MemberRequestDto;
import com.jabiz.erp.member.controller.dto.TokenDto;
import com.jabiz.erp.member.controller.dto.TokenResponseDto;
import com.jabiz.erp.member.domain.entity.Member;
import com.jabiz.erp.member.entry.TokenProvider;
import com.jabiz.erp.member.infra.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final MemberRepository memberRepository;

    @Transactional
    public TokenDto signup(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toMember(passwordEncoder);
        member.setId(memberRepository.save(member).getId());

        TokenDto tokenDto = new TokenDto();
        try {
            tokenDto = tokenProvider.generateTokenDto(Long.toString(member.getId()), "ROLE_USER");
            tokenDto.setId(member.getId());
            tokenDto.setNickname(member.getNickname());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return tokenDto;
    }

    @Transactional
    public TokenResponseDto signin(MemberRequestDto memberRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        Authentication authentication;
        TokenDto tokenDto = new TokenDto();
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            tokenDto = tokenProvider.generateTokenDto(authentication.getName(),
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")));
        } catch (Exception e) {
            throw new NotFoundException();
        }

        Member member = memberRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new NotFoundException());

//        RefreshToken refreshToken = RefreshToken.builder()
//                .id(authentication.getName())
//                .refreshToken(tokenDto.getRefreshToken())
//                .build();
//        refreshTokenRepository.save(refreshToken);

        return TokenResponseDto.of(tokenDto, member);
    }

}
