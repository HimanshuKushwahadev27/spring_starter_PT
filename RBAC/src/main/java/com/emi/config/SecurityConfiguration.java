package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter auth;
    private final AuthenticationProvider authProvider;
    
    @Bean
    SecurityFilterChain securtiyFilterChain(HttpSecurity http )throws Exception{
    	http
    	    .csrf(csrf -> csrf.disable())
    	    
    	    .authorizeHttpRequests(auth -> auth
    	    		.requestMatchers("/api/v1/auth/**")
    	    		.permitAll()
    	    		.anyRequest()
    	    		.authenticated()
    	    		)
    	    
    	    .sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    	    .authenticationProvider(authProvider)
    	    .addFilterBefore(auth , UsernamePasswordAuthenticationFilter.class)
    	    ;
    	    return http.build();
    }
}
