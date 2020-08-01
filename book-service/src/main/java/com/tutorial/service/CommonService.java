package com.tutorial.service;

import java.util.Collection;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommonService<T> {
	
	Collection<T> findAll();
	
	T findById(@PathVariable Integer id);
	
	T saveOrUpdate(@RequestBody T t);
	
	String deleteById(@PathVariable Integer id);
	
	String delete(@RequestBody T t);
}
