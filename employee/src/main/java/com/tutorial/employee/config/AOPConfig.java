package com.tutorial.employee.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPConfig {
	
	@Before(value = "execution(* com.tutorial.employee.controller.EmployeeController.*(..)) and args(id)")
	public void beforeAdvice(JoinPoint joinPoint, Integer id) {
		System.out.println("Before method:" + joinPoint.getSignature());
		System.out.println("Fetching Employee with id - " + id);
	}

	@After(value = "execution(* com.tutorial.employee.controller.EmployeeController.*(..)) and args(id)")
	public void afterAdvice(JoinPoint joinPoint, Integer id) {
		System.out.println("After method:" + joinPoint.getSignature());
		System.out.println("Successfully Fetched Employee with id - " + id);
	}
}
