package com.tutorial;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.tutorial.model.BookEntity;
import com.tutorial.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class RestTemplateTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	@Autowired
	BookRepository bookRepo;

	@Test
	public void exampleTest() throws IOException {

		RestTemplate tmplt = new RestTemplate();
		BookEntity book = bookRepo.findById(1).get();
		ResponseEntity<BookEntity> response = restTemplate.getForEntity("http://localhost:" + port + "/api/book/1",
				BookEntity.class);
		assertTrue(response.getBody().getAuthor().contains(book.getAuthor()));
	}

}
