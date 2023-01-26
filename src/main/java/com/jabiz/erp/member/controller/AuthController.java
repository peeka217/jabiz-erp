package com.jabiz.erp.member.controller;

import com.jabiz.erp.member.controller.dto.MemberRequestDto;
import com.jabiz.erp.member.controller.dto.TokenDto;
import com.jabiz.erp.member.controller.dto.TokenResponseDto;
import com.jabiz.erp.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDto> signin(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signin(memberRequestDto));
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "alive";
    }


}
