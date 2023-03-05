package com.jabiz.erp.member.entry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jabiz.erp.business.domain.constant.BusinessStateCode;
import com.jabiz.erp.exception.UnauthorizedAccessException;
import com.jabiz.erp.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {

    private final Map<String, String> MENU_CODES = ImmutableMap.<String, String>builder()
            .put("business/registration", "1100")
            .put("business/process", "1200")
            .put("worker/information", "2100")
            .put("report/funds", "4100")
            .build();

    private final List<String> PUBLIC_URI_PATTERN = ImmutableList.<String>builder()
            .add("public")
            .add("utility")
            .add("dashboard")
            .build();
    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = tokenProvider.resolveToken(request);
        String[] requestUriArr = request.getRequestURI().substring(4).split("/");

        if (PUBLIC_URI_PATTERN.contains(requestUriArr[0]))
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
