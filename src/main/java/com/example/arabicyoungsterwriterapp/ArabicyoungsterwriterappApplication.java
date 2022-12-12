package com.example.arabicyoungsterwriterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("google-services.json")
public class Arabicyoungsterwriterapp2Application {

	public static void main(String[] args) {

		SpringApplication.run(Arabicyoungsterwriterapp2Application.class, args);
	}

}
