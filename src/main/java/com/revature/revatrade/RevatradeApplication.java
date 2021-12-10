package com.revature.revatrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RevatradeApplication {
	public static void main(String[] args) {
		SpringApplication.run(RevatradeApplication.class, args);
	}

}
