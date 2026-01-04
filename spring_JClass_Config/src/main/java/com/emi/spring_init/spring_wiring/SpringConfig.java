package com.emi.spring_init.spring_wiring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.emi.rep.AccountRep;
import com.emi.rep.AccountRepository;
import com.emi.rep.CreditCrdRep;
import com.emi.service.AccountService;
import com.emi.service.AccountServiceImpl;

@Configuration
@ComponentScan(basePackages ="com.emi")
public class SpringConfig{
	
	@Bean("service")
	public AccountService getAccService() {
		return new AccountServiceImpl();
	}
	
//	@Bean
//	public AccountRep getRep() {
//		return new AccountRepository();
//	}
	
	@Bean("CreditCrd")
	@Lazy
	public AccountRep getCard() {
		return new CreditCrdRep();
	}
	
}