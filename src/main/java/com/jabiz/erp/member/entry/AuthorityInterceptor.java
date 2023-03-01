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
            "business/registration", "0100",
            "business/process", "0200",
            "worker/information", "1100",
            "report/funds", "3100"
    );
    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = tokenProvider.resolveToken(request);
        System.out.println(request.getRequestURI());
        String requestUriPrefix = request.getRequestURI().substring(4).split("/")[0]
                + "/" + request.getRequestURI().substring(4).split("/")[1];

        String[] accessible;
        if (accessToken != null) {
            accessible = ((String) tokenProvider.parseClaims(accessToken).get("accessible"))
                    .split("&");

            String menuCode = MENU_CODES.get(requestUriPrefix);

            if (menuCode != null && !Arrays.stream(accessible).anyMatch(menuCode::equals))
                throw new UnauthorizedAccessException();
        }

        return true;
    }

}
