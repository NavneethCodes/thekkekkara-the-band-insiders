package com.thekkekkara.insiders.identity;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class IdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Standardize timezone to match PostgreSQL's IANA database
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}
}
