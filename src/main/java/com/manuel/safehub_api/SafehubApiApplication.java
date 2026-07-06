package com.manuel.safehub_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SafehubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafehubApiApplication.class, args);
	}

}
