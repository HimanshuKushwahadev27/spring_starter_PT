package com.emi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class Demo1 {

	private final AuthService authService;

	@GetMapping("/admin")
	public ResponseEntity<?> helloAdmin(){
		if(authService.onlyAdmin()) {
		return ResponseEntity.ok("helloAdmin");
		}else {
			throw new IllegalStateException("Not authorized enough");
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> helloUser(){
		if(authService.onlyUser()) {
		return ResponseEntity.ok("helloUser");
		}else {
			throw new IllegalStateException("Not authorized enough");
		}
	}
}
