package com.emi.controller;

import org.springframework.http.MediaType;
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
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class Demo {

	private final AuthService authService;
	
	@PostMapping(value="/register",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public void register(@RequestBody RequestDto req) {
		 authService.registerUser(req);
	}
	
	@PostMapping(value="/login",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	public AuthResponse login(@RequestBody LoginDto log) {
		return authService.authenticateUser(log);
	}
}
