package com.stackoverflow.soq76090017.sec;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
        @Autowired
        MyEntryPoint myEntry;

        @Bean
        @Order(1)
        public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
                return http
                                .securityMatcher("/admin/**")
                                .authorizeHttpRequests(
                                                authorize -> authorize
                                                                .anyRequest().hasRole("ADMIN"))
                                .exceptionHandling(handling -> handling.authenticationEntryPoint(myEntry))
                                .httpBasic(withDefaults()).build();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .authorizeHttpRequests(
                                                authorize -> authorize
                                                                .anyRequest().authenticated())
                                .formLogin(withDefaults())
                                .httpBasic(withDefaults())
                                .build();
        }
}
