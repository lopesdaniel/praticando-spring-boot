package com.danieldev.api;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiConfigDocs() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.danieldev.api.resources"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo infoDocs() {
		return new ApiInfo("API Rest Spring Boot", "Projeto para praticar meus conhecimentos", "0.1", "Termos", new Contact("Daniel Viana", "danieldev.com.br", "viana.daniel02@gmail.com"), "MIT", "URL", new ArrayList<VendorExtension>());
	}
	
}
