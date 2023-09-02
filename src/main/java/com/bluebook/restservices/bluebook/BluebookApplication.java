package com.bluebook.restservices.bluebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

// This is a test project for spring boot
@SpringBootApplication
public class BluebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BluebookApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		
		AcceptHeaderLocaleResolver localeResolver
				=new AcceptHeaderLocaleResolver();
		
		localeResolver.setDefaultLocale(Locale.US);
		
		return localeResolver;
		
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return  messageSource;
	}
}
