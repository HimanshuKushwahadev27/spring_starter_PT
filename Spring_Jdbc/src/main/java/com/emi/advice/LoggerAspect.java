package com.emi.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggerAspect {
  //logging happens by hijacking the call that comes from the API to the particular service 
	//so what happens is a subclass is created of EmployeeServiceImpl at runtime which is called "dynamic weaving" 
	//and the method calls are intercepted by the subclass and the advice methods are executed before and after the actual method execution.
	
	@Pointcut("execution(* com.emi.service.EmployeeServiceImpl.*(..))")
	public void log() {
	}
	// Before and After advice for logging
	
	//before executing any method in EmployeeServiceImpl it will log a message
	//@org.aspectj.lang.annotation.Before("execution(* com.emi.service.EmployeeServiceImpl.*(..))")
	@org.aspectj.lang.annotation.Before("log()")
	public void beforeLog() {
		System.out.println("Before method execution - Logging started");
	}
	
	//after executing any method in EmployeeServiceImpl it will log a message
	//@org.aspectj.lang.annotation.After("execution(* com.emi.service.EmployeeServiceImpl.*(..))")
	@org.aspectj.lang.annotation.After("log()")
	public void afterLog() {
		System.out.println("After method execution - Logging ended");
	}
}
