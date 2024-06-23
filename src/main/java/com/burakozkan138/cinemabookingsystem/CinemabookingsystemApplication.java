package com.burakozkan138.cinemabookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableMongoAuditing // this annotation is necessary for @CreatedDate and @LastModifiedDate to work
@OpenAPIDefinition
public class CinemabookingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemabookingsystemApplication.class, args);
	}

}
