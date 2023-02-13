package com.jabiz.erp.member.entry;

import com.jabiz.erp.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Pre Handle");
        String accessToken = tokenProvider.resolveToken(request);


//        System.out.println(tokenProvider.parseClaims(accessToken).get("accessible"));
        return true;
    }

}
