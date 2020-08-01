package com.tutorial;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableFeignClients
public class BookApp {
	@Value("${server.ssl.trust-store}")
	private Resource keyStore;
	@Value("${server.ssl.trust-store-password}")
	private String keyStorePassword;

	public static void main(String[] args) {
//		System.setProperty("javax.net.ssl.trustStore","harishssl.p12");
//        System.setProperty("javax.net.ssl.trustStorePassword","vinnyrishi");
		SpringApplication.run(BookApp.class, args);
	}

	@Bean
	public RestTemplate restTemplate() throws Exception {
		SSLContext sslContext = new SSLContextBuilder()
				.loadTrustMaterial(keyStore.getURL(), keyStorePassword.toCharArray()).build();
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(factory);
	}

//	@Bean
//	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate = restTemplateBuilder.basicAuthentication("harish", "harish").build();
//		HttpComponentsClientHttpRequestFactory requestFactory = null;
//
//		try {
//			SSLContext sslContext = SSLContextBuilder.create()
//					.loadKeyMaterial(ResourceUtils.getFile("classpath:harishssl.p12"), allPassword.toCharArray(),
//							allPassword.toCharArray())
//					.build();
//			HttpClient httpClient = HttpClients.custom().setSslcontext(sslContext).setMaxConnTotal(Integer.valueOf(5))
//					.setMaxConnPerRoute(Integer.valueOf(5)).build();
//
//			requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//			requestFactory.setReadTimeout(Integer.valueOf(10000));
//			requestFactory.setConnectTimeout(Integer.valueOf(10000));
//
//			restTemplate.setRequestFactory(requestFactory);
//		} catch (Exception exception) {
//			System.out.println("Exception Occured while creating restTemplate " + exception);
//			exception.printStackTrace();
//		}
//		return restTemplate;
//	}

}
