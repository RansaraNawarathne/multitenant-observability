package com.aurora.observability.configuration;

import com.aurora.observability.utility.JwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class that sets up Spring Security for the application.
 * <p>
 * Configures method-level security, JWT-based authentication, and disables session state.
 * </p>
 * <p>
 * Only actuator endpoints are publicly accessible; all other requests require authentication.
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtConverter jwtConverter;

    /**
     * Constructs the SecurityConfig with a custom JwtConverter.
     *
     * @param jwtConverter a component used to convert JWT tokens to authentication objects
     */
    public SecurityConfig(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    /**
     * Configures the security filter chain for the application.
     * <ul>
     *   <li>Disables CSRF protection (since it's stateless)</li>
     *   <li>Permits all requests to `/actuator/**` endpoints</li>
     *   <li>Secures all other endpoints using JWT-based authentication</li>
     *   <li>Sets session management to stateless</li>
     * </ul>
     *
     * @param http the HttpSecurity object used to configure security rules
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtConverter)
                ))
                .sessionManagement(
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                );

        return http.build();
    }
}
