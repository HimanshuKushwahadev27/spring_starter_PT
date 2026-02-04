package com.emi.Redis_Demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.Redis_Demo.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
