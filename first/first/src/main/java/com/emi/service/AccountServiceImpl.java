package com.emi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emi.rep.AccountRep;
import com.emi.rep.CreditCrdRep;

@Component
public class AccountServiceImpl implements AccountService{
    
	public AccountServiceImpl() {
		System.out.println("Account Service constructor");
	}
	
	//ref of accountRep
	//using annotation instead of property tag , create a setter method at runtime
	
	//using qualifier to specify the particular class in case of multiple implementation of the same interface 
	//can also use byName
	@Autowired
	@Qualifier("AccRep")
	private AccountRep rep;
	
//	@Autowired
//	private AccountRep card;
	//setter to create the object of AccountRepository using IoC
//	public void setRep(AccountRep rep) {
//		System.out.println("Service setter .. init");
//		this.rep = rep;
//	}

    
	@Override
	public String creditService(float amt) {
		return rep.credit(amt);
	}
	
	
	
	
}