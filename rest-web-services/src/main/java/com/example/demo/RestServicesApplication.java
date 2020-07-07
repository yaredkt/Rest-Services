package com.example.demo;

import java.util.Locale;


import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.demo.user.UserRepository;
 
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= UserRepository.class)
public class RestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		
		AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
		
		localResolver.setDefaultLocale(Locale.US);
		
		return localResolver;		
		
	}
		
}
