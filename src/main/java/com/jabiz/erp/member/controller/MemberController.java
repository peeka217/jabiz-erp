package com.jabiz.erp.member.controller;

import com.jabiz.erp.member.controller.dto.MemberRequest;
import com.jabiz.erp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @PatchMapping("/password")
    public void changeMemberPassword(@RequestBody MemberRequest memberRequest) {
        memberService.changeMemberPassword(memberRequest);
    }

    @PostMapping("/signup")
    public String signup() {
        return "member!!";
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "alive";
    }
}
