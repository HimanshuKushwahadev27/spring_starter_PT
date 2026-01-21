package com.emi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.requestDto.LoginDto;
import com.emi.requestDto.RequestDto;
import com.emi.responseDto.AuthResponse;
import com.emi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class Demo {

	private final AuthService authService;
	
	@PostMapping("/register")
	public void register(@RequestBody RequestDto req) {
		 authService.registerUser(req);
	}
	
	@PostMapping("/login")
	public AuthResponse login(LoginDto log) {
		return authService.authenticateUser(log);
	}
}
