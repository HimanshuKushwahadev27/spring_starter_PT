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

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {


	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		     .csrf(csrf -> csrf.disable())
		     .authorizeHttpRequests(auth -> auth
		    		 .requestMatchers("/api/v1/secure/**")
		    		 .permitAll()
		    		 .anyRequest()
		    		 .authenticated())
		     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     .authenticationProvider(authProvider)
		     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
