package com.tutorial.employee.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroups;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.employee.client.IEmployeeController;
import com.tutorial.employee.config.CustomExceptionHandler;
import com.tutorial.employee.config.RecordNotFoundException;
import com.tutorial.employee.entity.EmployeeEntity;
import com.tutorial.employee.repository.EmployeeRepository;
import com.tutorial.employee.service.IEmployeeService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController implements IEmployeeController {

	@Autowired
	IEmployeeService empService;
	
	Logger log = LoggerFactory.getLogger(EmployeeController.class);

	public ResponseEntity<EmployeeEntity> createEmployee(EmployeeEntity emp) {
		
		
		EmployeeEntity response = empService.createEmployee(emp);
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<List<EmployeeEntity>> showEmployees() {
		Long startTime = Instant.now().toEpochMilli();
		List<EmployeeEntity> response = empService.showEmployees();
		Long endTime = Instant.now().toEpochMilli();
		Long elapsedTime = endTime - startTime;
		log.info("Time to fectech all Employees with cache: " + elapsedTime);
		return ResponseEntity.ok(response);

	}

	
	public ResponseEntity<EmployeeEntity> getEmployeById(Integer id) {

		log.trace("Get Employee by id: ", id);
		EmployeeEntity response = empService.getEmployeById(id);
		return ResponseEntity.ok(response);
	}

	
	public ResponseEntity<EmployeeEntity> updateEmployee(EmployeeEntity emp) {

		return ResponseEntity.ok(empService.updateEmployee(emp));
		
	}
 
	
	public ResponseEntity<Object> deleteEmployeById(Integer id) {
		Object response = empService.deleteEmployeById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<EmployeeEntity> getEmployeLogin(Integer id, String password) throws Exception {
		
		return ResponseEntity.ok(empService.getEmployeLogin(id, password));
	}
	
	public void clearCache() {
		empService.clearCache();
	}

	@Override
	public Object sendSimpleMail( String to, String subject, String body) {
		// TODO Auto-generated method stub
		return empService.sendSimpleMail(to, subject, body);
	}

}
