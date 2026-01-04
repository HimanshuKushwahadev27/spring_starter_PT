package com.emi.rep;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("AccRep")
public  class AccountRepository implements AccountRep{

	public AccountRepository() {
		System.out.println("Accrep constructor");
	}

	@Override
	public String credit(float amt) {
		return "Hello the amount of "+ amt +" has been credit to your account";
	}
	
	
}