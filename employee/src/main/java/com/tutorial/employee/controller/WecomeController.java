package com.tutorial.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class WecomeController {
	
	@Value("${server.port}")
	private String customPort;
	
	@Autowired
	Environment env;
	
	@GetMapping("/welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.ok("welcome to employee service with custom port: " + customPort + "or " + env.getProperty("server.port"));
	}
	
	@GetMapping("/invalid")
	public ResponseEntity<?> invalidRequest() {
		return ResponseEntity.ok("invalid request");
	}
}
