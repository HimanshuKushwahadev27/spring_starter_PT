package com.emi.spring_init.spring_wiring;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emi.service.AccountService;


public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        
        AccountService acc=(AccountService) ctx.getBean(AccountService.class);
        
        System.out.println(acc.creditService(1000));
        
        
    }
}
