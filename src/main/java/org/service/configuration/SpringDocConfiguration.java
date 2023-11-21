package org.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfiguration {

	@Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
	OpenAPI apiInfo() {
		return new OpenAPI().info(new Info().title("Service Request")
				.description("API specs for service attribute definition and capture service attribute  details  ")
				.contact(new Contact().name("Digit").email("info@egovernments.org")).version("1.0.0"));
	}
}