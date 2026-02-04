package com.emi.Redis_Demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.emi.Redis_Demo.Repo.CustomerRepo;
import com.emi.Redis_Demo.entity.Customer;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepo repo;
	
	public Customer addNew(Customer c) {
		return repo.save(c);
	}
	
	@Cacheable(value="customer", key="#root.args[0]")
	public Customer geyById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("User not here"));
	}
}
