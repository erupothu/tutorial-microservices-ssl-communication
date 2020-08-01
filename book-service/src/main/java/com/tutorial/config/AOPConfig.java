package com.tutorial.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//public class AOPConfig {
//	
//	@Before(value = "execution(* com.tutorial.service.BookServiceImpl.*(..)) and args(id)")
//	public void beforeAdvice(JoinPoint joinPoint, Integer id) {
//		System.out.println("Before method:" + joinPoint.getSignature());
//		System.out.println("Fetching Book with id - " + id);
//	}
//
//	@After(value = "execution(* com.tutorial.service.BookServiceImpl.*(..)) and args(id)")
//	public void afterAdvice(JoinPoint joinPoint, Integer id) {
//		System.out.println("After method:" + joinPoint.getSignature());
//		System.out.println("Successfully Fetched Book with id - " + id);
//	}
//
//}
