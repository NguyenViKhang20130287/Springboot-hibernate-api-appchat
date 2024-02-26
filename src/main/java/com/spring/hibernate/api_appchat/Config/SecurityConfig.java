package com.spring.hibernate.api_appchat.Config;

import com.spring.hibernate.api_appchat.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AuthService authService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(authService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(auth ->
                auth
                        // auth
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/sign-in").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/sign-up").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/sign-up/confirm").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/v1/auth/forgot-password/confirm").permitAll()
        );
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
