package com.siwoo.siwooaksweb;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class SiwooAksWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwooAksWebApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@RestController
	public static class MainController {

		@Autowired
		private RestTemplate restTemplate;

		@GetMapping("/")
		public String greeting() {
			return "hello kube";
		}

		@GetMapping("/students")
		public ResponseEntity<Map<String, Integer>> students() {
			ResponseEntity<Map<String, Integer>> entity =
					restTemplate.exchange("http://siwoo-aks-data:8081/students",
							HttpMethod.GET,
							null,
							new ParameterizedTypeReference<Map<String, Integer>>(){});
			return entity;
		}

	}
}
