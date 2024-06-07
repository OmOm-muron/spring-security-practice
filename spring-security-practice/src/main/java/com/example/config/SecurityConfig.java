package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
        ).formLogin(login -> login
                .loginProcessingUrl("/login")
                // If you define 'loginPage', default pages are disabled. ex) /login, /login?error, /login?logout
                //.loginPage("/login")
                .failureUrl("/login?error")
        ).logout(logout -> logout
                // Set logout url accessed by GET or POST method, but GET page is defined 'logout' by default
                // So if you set this other than 'logout', when send GET to the url, error will occur
                .logoutUrl("/logout")
        );
        return http.build();
    }
}
