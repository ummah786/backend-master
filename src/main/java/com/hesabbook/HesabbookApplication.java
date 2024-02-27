package com.hesabbook;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HesabbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(HesabbookApplication.class,args);
	}

	@GetMapping("/hello")
	public String value(){
		return "hllo";
	}
}
