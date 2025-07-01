package com.aurora.observability.configuration;

import com.aurora.observability.utility.ProjectContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration class that registers custom interceptors for request processing.
 * <p>
 * This configuration adds the {@link ProjectContextInterceptor} to all incoming HTTP requests
 * to extract and store project-specific context (e.g., project ID) for downstream use.
 * </p>
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ProjectContextInterceptor projectContextInterceptor;

    /**
     * Constructs a WebMvcConfig with the specified {@link ProjectContextInterceptor}.
     *
     * @param projectContextInterceptor the interceptor that extracts project context from requests
     */
    public WebMvcConfig(ProjectContextInterceptor projectContextInterceptor) {
        this.projectContextInterceptor = projectContextInterceptor;
    }

    /**
     * Registers the {@link ProjectContextInterceptor} with the interceptor registry,
     * applying it to all request paths in the application.
     *
     * @param registry the registry that holds all registered interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectContextInterceptor).addPathPatterns("/**");
    }
}
