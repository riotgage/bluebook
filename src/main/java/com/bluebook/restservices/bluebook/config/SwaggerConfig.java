package com.bluebook.restservices.bluebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.bluebook.restservices.bluebook.controllers"))
				.paths(PathSelectors.ant("/users/**"))
				.build();
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("User CRUD API")
				.description("Spring Rest API reference")
				.version("1.0")
				.build();
	}
	
	// Swagger MetaData: http://localhost:8080/v2/api-docs
	// Swagger MetaData: http://localhost:8080/swagger-ui.html
	
}
