package com.playwright.cucumber.testing.framework.playwright.hooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.playwright.cucumber.testing.framework.playwright.page.BaseClassPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberHooks {
    
    @Autowired @Lazy
    private BaseClassPage baseClassPage;

    @Before
    public void setUp(Scenario scenario){
        log.info("TestCase Starting : " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario){
        try {
            if(scenario.isFailed()){
                log.info("TestCase Failed : " + scenario.getName());
                this.baseClassPage.screenshot(scenario.getName().substring(0, 5));
                this.baseClassPage.closeAPI();
                this.baseClassPage.close();
            } else {
                log.info("TestCase Passed : " + scenario.getName());
                this.baseClassPage.closeAPI();
                this.baseClassPage.close();
            }
        } catch (Exception e) {
            log.error("Error in Cucumber Hooks : " + e);
        }
        

    }
}
