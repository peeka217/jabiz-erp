package com.jabiz.erp.member.controller;

import com.jabiz.erp.member.controller.dto.MemberRequest;
import com.jabiz.erp.member.controller.dto.AccessTokenResponse;
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
    public ResponseEntity<AccessTokenResponse> signup(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(authService.signup(memberRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AccessTokenResponse> signin(@RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(authService.signin(memberRequest));
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "alive";
    }


}
