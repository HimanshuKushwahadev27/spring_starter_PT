package com.emi.rep;


public class CreditCrdRep implements AccountRep{

	
	
	public CreditCrdRep() {
		System.out.println("CreditCard ...");
	}

	@Override
	public String credit(float amt) {
	   return "your due balance of amount "+amt+ " has been credited";
	}
	
}