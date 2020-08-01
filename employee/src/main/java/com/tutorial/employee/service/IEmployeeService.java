package com.tutorial.employee.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tutorial.employee.entity.EmployeeEntity;

public interface IEmployeeService {
	
	public EmployeeEntity createEmployee(@Valid @RequestBody EmployeeEntity emp);
	
	public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity emp);
	
	public List<EmployeeEntity> showEmployees();
	
	public EmployeeEntity getEmployeById(@PathVariable Integer id);
	
	public EmployeeEntity getEmployeLogin(@PathVariable Integer id, @PathVariable String password) throws Exception;
	
	public Object deleteEmployeById(@PathVariable Integer id);
	
	public void clearCache();
	
	public Object sendSimpleMail(String to, String subject, String body);

}
