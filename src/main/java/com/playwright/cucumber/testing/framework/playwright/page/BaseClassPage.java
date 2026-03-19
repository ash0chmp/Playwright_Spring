package com.playwright.cucumber.testing.framework.playwright.page;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.playwright.cucumber.testing.framework.playwright.config.driver.driverConfig;
import com.playwright.cucumber.testing.framework.playwright.config.playwrightconfig.EnvironmentalProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseClassPage {
    
    @Autowired
    protected driverConfig driver;

    @Autowired
    private EnvironmentalProperties environmentalProperties;

    @Autowired
    protected Page page;

    @Autowired
    protected APIResponse apiResponse;

    public void screenshot(String scenario) {
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(String.format(environmentalProperties.getSrcShotPath()+"%s.png", scenario))));
        } catch (Exception e) {
            log.info("Screenshot Captured failed");
        }
    }

    public void close() {
        driver.context().tracing().stop(new Tracing.StopOptions().setPath(Path.of("target/tracing/trace.zip")));
        page.context().browser().close();
    }

    public void closeAPI() {
        driver.apiRequestContext().dispose();
    }

    public void goTo() {
        page.navigate(environmentalProperties.getEnvUrl());
    }
}
