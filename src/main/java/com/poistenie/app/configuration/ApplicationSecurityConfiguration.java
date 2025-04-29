package com.poistenie.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfiguration {

    // Configures HTTP security rules and authentication mechanisms
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()

                // Endpoints restricted to users with ADMIN role
                .requestMatchers(
                        "/insured-persons/create",
                        "/insured-persons/edit/**",
                        "/insured-persons/delete/**"
                ).hasRole("ADMIN")

                // Endpoints accessible by users with USER or ADMIN roles
                .requestMatchers(
                        "/insured-persons",
                        "/insured-persons/detail/**"
                ).hasAnyRole("USER", "ADMIN")

                // Public endpoints accessible without authentication
                .requestMatchers(
                        "/styles/**", "/images/**", "/scripts/**", "/fonts/**",
                        "/about-us", "/", "/account/register", "/account/login"
                ).permitAll()

                // All other requests require authentication
                .anyRequest().authenticated()

                // Configure login page and authentication parameters
                .and()
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/insured-persons", true)
                .usernameParameter("email")
                .permitAll()

                // Configure logout behavior
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .permitAll()

                .and().build();
    }

    // Provides a password encoder bean using BCrypt hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
