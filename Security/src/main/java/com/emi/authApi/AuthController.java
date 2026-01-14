package com.emi.authApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.requestDto.AuthenticationRequest;
import com.emi.requestDto.RegisterRequest;
import com.emi.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import responseDto.AuthenticationResponse;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService service ;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest user){
		return ResponseEntity.ok(service.register(user));
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
		
	}
}
