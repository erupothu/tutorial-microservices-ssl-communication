package com.tutorial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//public class InterceptorConfig extends WebMvcConfigurerAdapter {
//	
//	@Autowired
//	CommonInterceptor commonInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(commonInterceptor);
////		registry.addWebRequestInterceptor("");
////		 registry.addResourceHandler("/docs/swagger/swagger-ui.html**")
////         .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
////		registry.addInterceptor(bookInterceptor); // to more interceptors
////		super.addInterceptors(registry);
//	}
//
//}
