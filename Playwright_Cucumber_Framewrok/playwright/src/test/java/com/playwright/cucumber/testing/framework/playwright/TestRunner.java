package com.playwright.cucumber.testing.framework.playwright;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.DataProvider;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberContextConfiguration
@SpringBootTest
@CucumberOptions(
    features = "classpath:features",
    glue = {"com.playwright.cucumber.testing.framework.playwright"},
    tags = "@abc",
    plugin = {"html:target/cucumber-reports/cucumber-html-report.html",
              "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
