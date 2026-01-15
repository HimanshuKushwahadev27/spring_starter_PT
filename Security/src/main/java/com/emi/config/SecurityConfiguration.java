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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthfilter;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		    .csrf(csrf ->csrf.disable())
		    
		    .authorizeHttpRequests(auth -> auth
		    	    .requestMatchers("/api/v1/auth","/api/v1/auth/**")  //request whihc will not need authentication
				    .permitAll() //permit them
				    .anyRequest()  // any else request need authentication
				    .authenticated())
		    
		    
		    //session should be stateless
		    .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    
		    //filter for filter chain
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(jwtAuthfilter , UsernamePasswordAuthenticationFilter.class);
		   
		
		
		return http.build();
	}
}
