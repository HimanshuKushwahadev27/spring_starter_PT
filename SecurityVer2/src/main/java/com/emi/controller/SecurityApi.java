package com.emi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.RequestDto.AuthRequest;
import com.emi.RequestDto.RegisterRequest;
import com.emi.ResponseDto.AuthenticateResponse;
import com.emi.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/secure")
@RequiredArgsConstructor
public class SecurityApi {

	private final AuthenticationService authenticationService;
	
	@PostMapping("/Register")
	public ResponseEntity<AuthenticateResponse> Register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(authenticationService.Register(request));
	}
	
	@PostMapping("/Authenticate")
	public ResponseEntity<AuthenticateResponse> Authenticate(@RequestBody AuthRequest request){
	return ResponseEntity.ok(authenticationService.Authenticate(request));
	}
	
}
