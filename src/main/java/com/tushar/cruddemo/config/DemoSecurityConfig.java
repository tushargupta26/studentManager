package com.tushar.cruddemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("STUDENT")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("STUDENT", "TEACHER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("STUDENT", "TEACHER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN")
        );
        http.httpBasic();

        // disable csrf
        // in general not required for stateless REST APIs
        http.csrf().disable();
        return http.build();
    }
}
