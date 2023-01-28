package com.jabiz.erp.util;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class SecurityUtil {

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getPrincipal().getClass().getTypeName()
                .equals("org.springframework.security.core.userdetails.User"))
            return null;

        return Long.parseLong(authentication.getName());
    }

}
