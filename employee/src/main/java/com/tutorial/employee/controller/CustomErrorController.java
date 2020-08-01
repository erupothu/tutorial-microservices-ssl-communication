package com.tutorial.employee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController{

	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	@GetMapping(value="/error")
    public String error() {
        return "invalid api call";
    }

}
