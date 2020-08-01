package com.tutorial.employee.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ErrorResponse {
	
	//General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;
    
	public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
}
