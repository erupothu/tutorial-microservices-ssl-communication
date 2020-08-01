package com.tutorial.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.model.BookEntity;
import com.tutorial.service.CommonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/book/")
public class BookController implements CommonController<BookEntity> {

	@Value("${server.port}")
	private String port;
	
//	@Value("${eureka.instance.instance-id}")
//	private String instanceId;

	Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private CommonService<BookEntity> bookService;

	public String getPort() throws InterruptedException {
		Thread.sleep(3000);
		return "Load balancing with mulitple " +" and port: "+ port;
	}

	public ResponseEntity<Collection<BookEntity>> findAll() {
		Long startTime = Instant.now().toEpochMilli();
		log.info("Book Resource findAll");
		Collection<BookEntity> result = bookService.findAll();
		Long endTime = Instant.now().toEpochMilli();
		Long elapsedTime = endTime - startTime;
		log.info("Time to fectech all Books with cache: " + elapsedTime);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<BookEntity> findById(Integer id) {
		log.info("Book Resource find by id");
		Long startTime = Instant.now().toEpochMilli();
		BookEntity result = bookService.findById(id);
		Long endTime = Instant.now().toEpochMilli();
		Long elapsedTime = endTime - startTime;
		log.info("Time to fectech a Book with cache : " + elapsedTime);
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<BookEntity> save(BookEntity t) {
		log.info("Book Resource Save");
		return new ResponseEntity<>(bookService.saveOrUpdate(t), HttpStatus.CREATED);
	}

	public ResponseEntity<BookEntity> update(BookEntity t) {
		log.info("Book Resource update");
		return ResponseEntity.ok(bookService.saveOrUpdate(t));
	}

	public ResponseEntity<String> deleteById(Integer id) {
		log.info("Book Resource Delete by id");
		return ResponseEntity.ok(bookService.deleteById(id));
	}

	public ResponseEntity<String> delete(BookEntity t) {
		log.info("Book Resource Delete");
		return ResponseEntity.ok(bookService.delete(t));
	}

	public ResponseEntity<String> invalid() {
		log.info("Book Resource invalid");
		return ResponseEntity.ok("invalid request");
	}

	public ResponseEntity<List<String>> sessionManagement(String msg, HttpServletRequest request) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList();
			request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		}
		messages.add("session: "+ request.getSession().getId() + " and msg:" + msg);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
		
		return ResponseEntity.ok(messages);
	}
	
	
	
	

}
