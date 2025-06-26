package com.aurora.observability.utility;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Component
public class ProjectContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) return true;

        Map<String, String> uriTemplateVars =
                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (uriTemplateVars != null && uriTemplateVars.containsKey("projectId")) {
            String projectId = uriTemplateVars.get("projectId");
            MDC.put("projectId", projectId); // Add to MDC
            ProjectContextHolder.setProjectKey(projectId); // Optional
        } else {
            MDC.put("projectId", "unknown");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        ProjectContextHolder.clear();
    }
}
