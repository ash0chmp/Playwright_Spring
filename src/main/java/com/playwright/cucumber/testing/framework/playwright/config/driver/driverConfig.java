package com.playwright.cucumber.testing.framework.playwright.config.driver;

import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.playwright.cucumber.testing.framework.playwright.config.playwrightconfig.EnvironmentalProperties;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class driverConfig {
    
    private final Playwright playwright = Playwright.create();
    private Browser browser;
    private BrowserContext browserContext;
    public static Page page = null;
    private APIRequest apiRequest;
    private APIRequestContext apiRequestContext;
    public static APIResponse apiResponse = null;

    @Autowired
    private EnvironmentalProperties environmentalProperties;

    @Bean
    Page page() {
        if("H".equalsIgnoreCase(environmentalProperties.getCompilationMode())){
            if("Chrome".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.chromium().launch();
            } else if("Edge".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge"));
            } else if("Webkit".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.webkit().launch();
            } else{
                browser = playwright.firefox().launch();
            }
        } else {
            if("Chrome".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            } else if("Edge".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));
            } else if("Webkit".equalsIgnoreCase(environmentalProperties.getBrowser())){
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            } else{
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            }
        }
        browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(environmentalProperties.getVideoPath()))); 
        browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
        page = browserContext.newPage();
        return page;
    }

    public BrowserContext context() {
        return context();
    }

    public APIResponse apiResponse(){
        apiRequest = playwright.request();
        apiRequestContext = apiRequest.newContext();
        apiResponse = apiRequestContext.get(environmentalProperties.getApiUrl());
        return apiResponse;
    }

    public APIRequestContext apiRequestContext() {
        return apiRequestContext;
    }
}
