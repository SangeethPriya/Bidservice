package com.auction.management.buyer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auction.management.buyer.util.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docketConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()				
				.apis(RequestHandlerSelectors.basePackage(Constants.BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());						
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title(Constants.SWAGGER_TITLE)
				.description(Constants.SWAGGER_DESCRIPTION)
				.termsOfServiceUrl(Constants.TERMS_OF_SERVICE_URL)
				.contact(new Contact(Constants.CONTACT_NAME, Constants.CONTACT_URL, Constants.CONTACT_EMAIL))
				.license(Constants.LICENSE)
				.licenseUrl(Constants.LICENSE_URL)
				.version(Constants.VERSION)
				.build();
	}
}
