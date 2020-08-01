package com.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.model.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>{

}
