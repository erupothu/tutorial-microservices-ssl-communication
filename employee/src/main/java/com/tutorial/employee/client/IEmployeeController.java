package com.tutorial.employee.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tutorial.employee.entity.EmployeeEntity;

public interface IEmployeeController {
	
	@PostMapping("/create-employee")
	public ResponseEntity<EmployeeEntity> createEmployee(@Valid @RequestBody EmployeeEntity emp);
	
	@PutMapping("/update-employee")
	public ResponseEntity<EmployeeEntity> updateEmployee(@RequestBody EmployeeEntity emp);
	
	@GetMapping("/show-employees")
	public ResponseEntity<List<EmployeeEntity>> showEmployees();
	
	@GetMapping("/show-employe/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeById(@PathVariable Integer id);
	
	@GetMapping("/validate-employe/{id}/{password}")
	public ResponseEntity<EmployeeEntity> getEmployeLogin(@PathVariable Integer id, @PathVariable String password) throws Exception;
	
	@DeleteMapping("/delete-employe/{id}")
	public ResponseEntity<Object> deleteEmployeById(@PathVariable Integer id);
	
	@GetMapping("/clear-emp-cache")
	public void clearCache();
	
	@GetMapping("/send-mail/{to}/{subject}/{body}")
	public Object sendSimpleMail(@PathVariable String to, @PathVariable String subject, @PathVariable String body);
	
	

}
