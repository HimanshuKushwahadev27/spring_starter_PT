package com.emi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.emi.rep.AccountRep;


public class AccountServiceImpl implements AccountService{
    
	
	public AccountServiceImpl() {
		System.out.println("Account Service constructor");
	}
	
	//ref of accountRep
	@Autowired
	@Qualifier("CreditCrd")
	private AccountRep card;
	
	//setter to create the object of AccountRepository using IoC


	@Override
	public String creditService(float amt) {
		return card.credit(amt);
	}
	
	
}