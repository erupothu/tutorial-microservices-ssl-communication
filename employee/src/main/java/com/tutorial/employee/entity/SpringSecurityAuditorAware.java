package com.tutorial.employee.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class SpringSecurityAuditorAware implements AuditorAware<EmployeeEntity> {

	@Override
	public Optional<EmployeeEntity> getCurrentAuditor() {
		// TODO Auto-generated method stub
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		  if (authentication == null || !authentication.isAuthenticated()) {
//		   return null;
//		  }
//
//		  return ((EmployeeEntity) authentication.getPrincipal());
		  return null;
	}

}
