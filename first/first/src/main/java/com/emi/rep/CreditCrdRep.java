package com.emi.rep;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("CreditCrd")
public class CreditCrdRep implements AccountRep{

	
	public CreditCrdRep() {
		System.out.println("Credit Card ...");
	}

	@Override
	public String credit(float amt) {
		return "your bill for card 1232XXX98 of amount "+amt+" has been paid";
	}
	
}