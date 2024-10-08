package com.ufm.QuickMart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Habilita CORS para el frontend en localhost:3000
public class QuickMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickMartApplication.class, args);
	}
	@RequestMapping("/api/hola")
	public String helloWorld() {
		return "WELCOME!";
	}
}
