package org.service;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

import com.fasterxml.jackson.databind.Module;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@ComponentScan(basePackages = { "org.service", "org.service.api",
		"org.service.configuration" }, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class SpringApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
	}

	@Bean(name = "org.openapitools.SpringApplication.jsonNullableModule")
	public Module jsonNullableModule() {
		return new JsonNullableModule();
	}

}