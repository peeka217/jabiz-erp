package com.jabiz.erp.member.entry;

import com.jabiz.erp.exception.UnauthorizedAccessException;
import com.jabiz.erp.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final Map<String, String> MENU_CODES = Map.of(
//            "utility/selectbox", "0100",
//            "foundation/dashboard", "0200",

            "business/registration", "1100",
            "business/process", "1200",
            "worker/information", "2100",
            "report/funds", "3100"
    );
    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = tokenProvider.resolveToken(request);
        String[] requestUriArr = request.getRequestURI().substring(4).split("/");

        if (requestUriArr[0].equals("public") || requestUriArr[0].equals("utility"))
            return true;

        String requestUriPrefix = requestUriArr[0] + "/" + requestUriArr[1];

        String[] accessible;
        accessible = ((String) tokenProvider.parseClaims(accessToken).get("accessible"))
                .split("&");

        String menuCode = MENU_CODES.get(requestUriPrefix);

        if (menuCode != null && !Arrays.stream(accessible).anyMatch(menuCode::equals))
            throw new UnauthorizedAccessException();

        return true;
    }

}
