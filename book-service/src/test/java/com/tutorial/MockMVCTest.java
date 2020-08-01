package com.tutorial;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getBookByIdWithMockMVC() throws Exception  {
		 MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/1").accept(MediaType.APPLICATION_JSON)).andReturn();
		 System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getBookByIdDirect() throws Exception {
		mockMvc.perform(get("/api/book/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("java")).andExpect(jsonPath("$.author").value("harish"))
				.andExpect(jsonPath("$.code").value("123")).andExpect(jsonPath("$.category").value("science"));
		System.out.println("success");

	}

}
