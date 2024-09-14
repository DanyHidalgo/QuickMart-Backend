package com.ufm.QuickMart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController

public class QuickMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickMartApplication.class, args);
	}

	@RequestMapping("/api/hola")
	public String helloWorld(){
		return "Hello World";
	}
}
