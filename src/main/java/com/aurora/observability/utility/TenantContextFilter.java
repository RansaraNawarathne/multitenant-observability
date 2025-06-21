package com.aurora.observability.utility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantContextFilter extends OncePerRequestFilter {

    @Value("${multitenancy.header-name}")
    private String tenantHeader;

    @Value("${multitenancy.default-tenant}")
    private String defaultTenant;

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tenantIdentifier = request.getHeader(tenantHeader);

        if (StringUtils.hasText(tenantIdentifier)) {
            TenantContextHolder.setTenantKey(tenantIdentifier);
        } else {
            TenantContextHolder.setTenantKey(defaultTenant);
        }

        try {
            String userId = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                Jwt jwt = jwtAuth.getToken();
                userId = jwt.getClaimAsString("sub");
            }

            MDC.put("tenantId", tenantIdentifier != null ? tenantIdentifier : defaultTenant);
            MDC.put("userId", userId != null ? userId : "anonymous");
            MDC.put("asset", serviceName);
            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.resetTenant();
            MDC.clear();
        }
    }
}
