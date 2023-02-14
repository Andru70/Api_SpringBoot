package com.api.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiMysqlSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMysqlSpringbootApplication.class, args);
	}
}
