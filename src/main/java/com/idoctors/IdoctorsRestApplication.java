package com.idoctors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class IdoctorsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdoctorsRestApplication.class, args);
	}
}
