package com.transactionsroutine;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket( DocumentationType.SWAGGER_2 )
				.select()
				.apis( RequestHandlerSelectors.basePackage( "com.transactionsroutine.controller" ) )
				.paths( PathSelectors.any() )
				.build()
				.useDefaultResponseMessages( false )
				.globalResponseMessage( RequestMethod.GET, responseMessageForGET() )
				.globalResponseMessage( RequestMethod.POST, responseMessageForPOST() );
	}

	private List<ResponseMessage> responseMessageForGET() {
		return asList(
				new ResponseMessageBuilder()
						.code( 200 )
						.message( "Item found" )
						.build(),
				new ResponseMessageBuilder()
						.code( 404 )
						.message( "Item not found" )
						.build() );
	}

	private List<ResponseMessage> responseMessageForPOST() {
		return asList(
				new ResponseMessageBuilder()
						.code( 200 )
						.message( "Created" )
						.build(),
				new ResponseMessageBuilder()
						.code( 400 )
						.message( "Invalid parameter" )
						.build() );
	}
}
