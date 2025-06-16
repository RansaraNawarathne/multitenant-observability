package com.aurora.observability.configuration;

import com.aurora.observability.utility.ProjectContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ProjectContextInterceptor projectContextInterceptor;

    public WebMvcConfig(ProjectContextInterceptor projectContextInterceptor) {
        this.projectContextInterceptor = projectContextInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectContextInterceptor).addPathPatterns("/**");
    }
}
