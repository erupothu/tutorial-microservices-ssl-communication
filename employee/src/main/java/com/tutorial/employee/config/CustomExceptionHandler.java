package com.tutorial.employee.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tutorial.employee.entity.ErrorResponse;


@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
//    
//    @ExceptionHandler({ ConstraintViolationException.class})
//    public ResponseEntity<Object> yourExceptionHandler(ConstraintViolationException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
//    	List<String> details = new ArrayList<>();
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
//        	details.add(constraintViolation.getMessage());
//        }
//        ErrorResponse error = new ErrorResponse("Validation Failed", details);
//        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//    }
    
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<Map<String, Object>> yourExceptionHandler(MethodArgumentNotValidException e) {
//        Map<String, Object> response = new HashMap<String, Object>();
//        Map<String, String> errors = new HashMap<String, String>();
//        BindingResult bindingResult = e.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        for (FieldError fieldError : fieldErrors) {
//            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        response.put("error", errors);
//        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//    }
//    
//    @ExceptionHandler({ ConstraintViolationException.class})
//    public ResponseEntity<Map<String, Object>> yourExceptionHandler2(ConstraintViolationException e) {
//        Map<String, Object> response = new HashMap<String, Object>();
//        Map<String, String> errors = new HashMap<String, String>();
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
//            errors.put(constraintViolation.getPropertyPath().toString() , constraintViolation.getMessage());
//        }
//        response.put("error", errors);
//        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//    }

}
