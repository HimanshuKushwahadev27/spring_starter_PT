package com.emi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.entity.User;
import com.emi.repo.UserRepo;
import com.emi.requestDto.AuthenticationRequest;
import com.emi.requestDto.RegisterRequest;

import lombok.RequiredArgsConstructor;
import responseDto.AuthenticationResponse;
import com.emi.enums.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepo repository;
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user=new User();
				user.setFirstName(request.getFirstName());
				user.setLastName(request.getLastName());
				user.setEmail(request.getEmail());
				user.setPassword(passwordEncoder.encode(request.getPassword()));
				user.setRole(Role.USER);
				user.setAccountNonLocked(true);
				user.setEnabled(true);
				
		repository.save(user);
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
				
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(), request.getPassword()
			)		
		);
		var user=repository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		var jwtToken=jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
}



	

