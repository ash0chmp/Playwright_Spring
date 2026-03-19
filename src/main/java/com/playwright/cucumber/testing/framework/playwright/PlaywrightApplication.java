package com.playwright.cucumber.testing.framework.playwright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.playwright.cucumber.testing.framework.playwright.config.playwrightconfig.EnvironmentalProperties;

@EnableConfigurationProperties(EnvironmentalProperties.class)
@SpringBootApplication
public class PlaywrightApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaywrightApplication.class, args);
	}

}
