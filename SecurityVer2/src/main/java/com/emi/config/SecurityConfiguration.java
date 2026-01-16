package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;


import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {


	private final JwtAuthFilter jwtAuthFilter;
	private final AuthenticationProvider authProvider;
    private final LogoutHandler logoutHandler;
	
	
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
		     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		     .logout(logout -> logout
		    		 .logoutUrl("/api/v1/secure/logout")
		    		 .addLogoutHandler(logoutHandler)
		    		 .logoutSuccessHandler((request , response , authentication) -> 
		    		            SecurityContextHolder.clearContext()))
		     ;
		
		return http.build();
	}
}
