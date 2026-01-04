package com.emi.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.service.AccountService;

@RestController
public class Spring_Controller{
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/test")
	public String test() {
		return service.creditService(5000);
	}
}