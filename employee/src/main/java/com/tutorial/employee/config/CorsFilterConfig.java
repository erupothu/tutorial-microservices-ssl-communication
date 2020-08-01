package com.tutorial.employee.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfig {
	
	@Bean
	public FilterRegistrationBean<Filter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
		config.addAllowedOrigin("*"); 
		config.addAllowedHeader("*"); //"Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"
		config.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "DELETE")); //"Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"
//		config.setMaxAge((long) 3600); //"Access-Control-Max-Age", "3600"
		
		source.registerCorsConfiguration("/api/**", config);
//		return new CorsFilter(source);
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}


}
