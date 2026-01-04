package com.emi.service;

import com.emi.rep.AccountRep;


public class AccountServiceImpl implements AccountService{
    
	
	public AccountServiceImpl() {
		System.out.println("Account Service constructor");
	}
	
	//ref of accountRep
	private AccountRep rep;
	
	//setter to create the object of AccountRepository using IoC
	public void setRep(AccountRep rep) {
		System.out.println("Service setter .. init");
		this.rep = rep;
	}


	@Override
	public String creditService(float amt) {
		return rep.credit(amt);
	}
	
	
}