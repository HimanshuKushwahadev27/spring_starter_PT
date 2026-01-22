package com.emi.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.entity.User;
import com.emi.repo.UserRepo;
import com.emi.requestDto.LoginDto;
import com.emi.requestDto.RequestDto;
import com.emi.responseDto.AuthResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	public AuthResponse authenticateUser(LoginDto request) {
			var user=userRepo.findByEmail
							(request.getEmail()).
							orElseThrow(() -> new UsernameNotFoundException("user is not here"));
			

			
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		

		
		var jwtToken=jwtService.generateToken(user);
		return AuthResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public void registerUser(RequestDto request) {
		
		System.out.println("in register");
		
		var user=new User();
				user.setFirstName(request.getFirstName());
				user.setLastName(request.getLastName());
				user.setEmail(request.getEmail());
				user.setRole(request.getRoles());
				user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepo.save(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public boolean onlyAdmin() {
		return true;
	}
	
	
	@PreAuthorize("hasRole('USER')")
	public boolean onlyUser() {
		return true;
	}
	
}
