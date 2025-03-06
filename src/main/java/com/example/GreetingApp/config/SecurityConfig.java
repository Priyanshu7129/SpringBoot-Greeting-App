package com.example.GreetingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/auth/register")) // ✅ Allows public access
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/h2-console/**").permitAll()  // ✅ Permit registration & H2
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // ✅ Fixes H2 Console
                .build();
    }
}
