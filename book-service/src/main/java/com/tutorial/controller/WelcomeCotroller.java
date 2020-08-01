package com.tutorial.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/book/")
public class WelcomeCotroller {
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("welcome")
	public ResponseEntity<?> welcome() {
		
		 String authStr = "harish:harish";
		    String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());

		    // create headers
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Authorization", "Basic " + base64Creds);

		    // create request
		    HttpEntity request = new HttpEntity(headers);

		    // make a request
		    ResponseEntity<String> response = restTemplate.exchange("https://localhost:8081/api/welcome", HttpMethod.GET, request, String.class);
//		ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:8081/api/welcome", String.class);
				return ResponseEntity.ok("welcome to books service"+ response.getBody());
	}
}
