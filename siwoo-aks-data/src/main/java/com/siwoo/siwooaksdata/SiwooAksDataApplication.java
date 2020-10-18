package com.siwoo.siwooaksdata;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SiwooAksDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwooAksDataApplication.class, args);
	}

	@RestController
	public static class MainController {
		@Autowired
		private Environment environment;

		@GetMapping("/env")
		public ResponseEntity<Map<String, String>> env() {
			Map<String, String> map = new HashMap<>();
			for (String key: Arrays.asList("db_url", "db_username"))
				map.put(key, environment.getProperty(key));
			return ResponseEntity.ok(map);
		}
	}

	@RestController
	public static class StudentsController {
		Map<String, Integer> db = ImmutableMap.of("Mark", 21, "Reo", 24, "Jenny", 27);

		@GetMapping("/students")
		public ResponseEntity<Map<String, Integer>> students() {
			return ResponseEntity.ok(db);
		}
	}
}
