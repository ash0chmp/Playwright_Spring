package com.playwright.cucumber.testing.framework.playwright;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ComponentScan
@EnableAutoConfiguration
public class TestMain {
    
    public static void main(String[] args) {
        String tagName = "@LetsPlay";

        for(String testTag : args) {
            tagName = testTag;
        }

        log.info("TestMain starting TestCase Tag : " + tagName);
        String[] cucumberOptionsArguments = {"--threads","1","classpath:features","--glue","com.playwright.cucumber.testing.framework.playwright","--tags",tagName,"--plugin","html:target/cucumber-reports/cucumber-html-report.html","--plugin","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"};
        io.cucumber.core.cli.Main.main(cucumberOptionsArguments);
    }
}
