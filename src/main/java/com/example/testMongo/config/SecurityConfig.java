package com.example.testMongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
            authorizeRequests
                    .requestMatchers("/", "user/info").permitAll()
                    .requestMatchers("/", "/oauth2/authorization/google").permitAll()
                    .anyRequest().authenticated()
        )
        .oauth2Login(oauth2Login ->
            oauth2Login
                .loginPage("/oauth2/authorization/google") // Specify the custom login page URL
        );
        return http.build();
    }
}
