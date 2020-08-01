package com.tutorial.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tutorial.model.BookEntity;
import com.tutorial.repository.BookRepository;

@Service
public class BookServiceImpl implements CommonService<BookEntity> {
	
	@Autowired
	BookRepository bookRepo;

	@CachePut(value = "books")
	public Collection<BookEntity> findAll() {
		return bookRepo.findAll();
	}

	@CachePut(value = "books", key = "#id")
	public BookEntity findById(Integer id) {
		return bookRepo.findById(id).isPresent()?bookRepo.findById(id).get():null;
	}

	public BookEntity saveOrUpdate(BookEntity t) {
		return bookRepo.save(t);
	}

	public String deleteById(Integer id) {
		bookRepo.deleteById(id);
		return "successful";
	}

	public String delete(BookEntity t) {
		bookRepo.delete(t);
		return "successful";
	}
	
//	@Scheduled(cron = "*/5 * * * * *") // schedule to run every 15sec
	public void scheduleFetchBook() {
		BookEntity book = bookRepo.findById(1).isPresent()?bookRepo.findById(1).get():null;
		System.out.println("scheduled get book @"+ System.currentTimeMillis()+", book"+ book);
	}
	
	

}
