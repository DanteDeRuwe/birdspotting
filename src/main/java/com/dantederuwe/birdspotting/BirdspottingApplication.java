package com.dantederuwe.birdspotting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class BirdspottingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirdspottingApplication.class, args);
	}

}
