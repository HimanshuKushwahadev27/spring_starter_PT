package com.emi.rep;


public  class AccountRepository implements AccountRep{

	public AccountRepository() {
		System.out.println("Acc rep constructor");
	}

	@Override
	public String credit(float amt) {
		return "Hello the amount of "+ amt +" has been credit to your account";
	}
	
	
}