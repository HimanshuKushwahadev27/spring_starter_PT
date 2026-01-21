package com.emi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.emi.principle.UserPrinciple;
import com.emi.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final UserRepo userRepo;
	
	@Bean
	UserDetailsService userDetailService() {
		return username -> new UserPrinciple(
				userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"))
				);
	}
	
	@Bean
	AuthenticationProvider authenticationPorvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider(userDetailService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration auth) {
		return auth.getAuthenticationManager();
	}
	
	
}
