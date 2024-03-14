package com.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            csrf.ignoringRequestMatchers(request -> {
                String path = request.getServletPath();
                return path.startsWith("/eureka/");
            });
        });
        return http.build();
    }
}
