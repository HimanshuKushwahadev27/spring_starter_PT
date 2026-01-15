package com.emi.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.RequestDto.AuthRequest;
import com.emi.RequestDto.RegisterRequest;
import com.emi.ResponseDto.AuthenticateResponse;
import com.emi.entity.User;
import com.emi.enums.Role;
import com.emi.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepo repository;
	private final PasswordEncoder passwordEncoder;
	
	private final JwtServices jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticateResponse Register(RegisterRequest request) {
		var user=new User();
		  user.setAccountNonLocked(true);
		  user.setEmail(request.getEmail());
		  user.setEnabled(true);
		  user.setFirstName(request.getFirstName());
		  user.setLastName(request.getLastName());
		  user.setPassword(passwordEncoder.encode(request.getPassword()));
		  user.setRole(Role.USER);
		  
	 repository.save(user);
	 var jwtToken=jwtService.generateToken(user);
	 return AuthenticateResponse.builder()
			 .token(jwtToken)
			 .build();
	}
	
	public AuthenticateResponse Authenticate(AuthRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail() , request.getPassword()));
		
		var user=repository.findByEmail(request.getEmail())
				 .orElseThrow(() -> new UsernameNotFoundException("user is not here"));
		
		var jwtToken=jwtService.generateToken(user);
		return AuthenticateResponse.builder()
				.token(jwtToken)
				.build();
	}
}
