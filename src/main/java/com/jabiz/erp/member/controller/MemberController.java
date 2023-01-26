package com.jabiz.erp.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

//    private final MemberService memberService;



    @PostMapping("/signup")
    public String signup() {
        return "member!!";
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "alive";
    }
}
