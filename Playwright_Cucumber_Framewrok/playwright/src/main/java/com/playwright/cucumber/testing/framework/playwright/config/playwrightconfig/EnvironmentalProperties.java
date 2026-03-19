package com.playwright.cucumber.testing.framework.playwright.config.playwrightconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "playwright.cucumber")
@Data
public class EnvironmentalProperties {
    
    private String compilationMode;

    private String browser;

    private String envUrl;

    private String testData;

    private String videoPath;

    private String srcShotPath;
    
    private String apiUrl;
}
