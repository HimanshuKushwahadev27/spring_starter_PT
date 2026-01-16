package com.emi.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emi.RequestDto.AuthRequest;
import com.emi.RequestDto.RegisterRequest;
import com.emi.ResponseDto.AuthenticateResponse;
import com.emi.entity.Token;
import com.emi.entity.User;
import com.emi.enums.Role;
import com.emi.enums.TokenType;
import com.emi.repo.TokenRepo;
import com.emi.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final TokenRepo tokenRepo;
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
		  
	 var savedUser=repository.save(user);
	 var jwtToken=jwtService.generateToken(user);
	 saveUserToken(savedUser ,jwtToken);
	 return AuthenticateResponse.builder()
			 .token(jwtToken)
			 .build();
	}
	
	
	public void saveUserToken(User user , String jwtToken) {
	 var token=Token.builder()
			 .user(user)
			 .token(jwtToken)
			 .type(TokenType.BEARER)
			 .revoked(false)
			 .expired(false)
			 .build();
	 tokenRepo.save(token);
	}
	
	private void revokeAllUserToken(User user) {
		var validToken=tokenRepo.findAllValidTokenByUser(user.getUser_Id());
		if(validToken==null) {
			return;
		}
		validToken.forEach(t ->{
		  t.setExpired(true);
		  t.setRevoked(true);
		});
	}
	
	public AuthenticateResponse Authenticate(AuthRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail() , request.getPassword()));
		
		var user=repository.findByEmail(request.getEmail())
				 .orElseThrow(() -> new UsernameNotFoundException("user is not here"));
		
		revokeAllUserToken( user);
		var jwtToken=jwtService.generateToken(user);
		saveUserToken(user,jwtToken);
		return AuthenticateResponse.builder()
				.token(jwtToken)
				.build();
	}
}
