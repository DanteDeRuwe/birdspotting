package com.dantederuwe.birdspotting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class BirdspottingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BirdspottingApplication.class, args);
	}

	@Bean
	public MessageSource messageSource(){
		var source = new ResourceBundleMessageSource();
		source.setBasename("validationmessages");
		return source;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
	}
}
