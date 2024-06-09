package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    
    @Value("${spring.datasource.username}")
    private String databaseUsername;
    
    @Value("${spring.datasource.password}")
    private String databasePassword;
    
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
        ).formLogin(login -> login
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")
        ).logout(logout -> logout
                .logoutUrl("/logout")
        );
        return http.build();
    }
    
    @Bean
    protected PasswordEncoder passwordEncoder() {
        // in default constructor, encoding strength is 10
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    protected DataSource dataSource() {
        return new DriverManagerDataSource(databaseUrl, databaseUsername, databasePassword);
    }
    
    @Bean
    protected UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager jdbcManager = new JdbcUserDetailsManager(dataSource);
        // this is set to false by default, so you need not set it
        jdbcManager.setEnableGroups(false);
        // override query for find user
        jdbcManager.setUsersByUsernameQuery(
                "SELECT username, password, 'true' as enabled FROM users WHERE username = ?"
        );
        // override query for find role(authority)
        jdbcManager.setAuthoritiesByUsernameQuery(
                "select username,authority from authorities where username = ?"
        );
        return jdbcManager;
    }
}
