package com.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Spring web flux crud example",
		version = "1.0"
		)
		)
public class SpringBootReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactiveApplication.class, args);
	}

}
