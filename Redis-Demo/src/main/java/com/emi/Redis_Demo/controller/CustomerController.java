package com.emi.Redis_Demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emi.Redis_Demo.entity.Customer;
import com.emi.Redis_Demo.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService service;
	
	@PostMapping()
	public Customer addNew(@RequestBody Customer c) {
		return service.addNew(c);
	}
	
	@GetMapping("/{id}")
	public Customer get(@PathVariable Long id) {
		return service.geyById(id);
	}
	
}
