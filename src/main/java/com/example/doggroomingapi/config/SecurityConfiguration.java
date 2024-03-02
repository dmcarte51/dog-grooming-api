package com.example.doggroomingapi.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
This class is a configuration for Spring Security. It essentially sets rules for securing
http requests.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable) // Disabled because of stateless authentication; it's unnecessary
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
        ) // Authorize http requests starting with /users/ and check all others for valid token
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session to stateless
        .authenticationProvider(authProvider) // Spring Authentication provider
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Filters by jwt, then username/password

        return http.build(); // Return the built authenticated filtered http request
    }

}
