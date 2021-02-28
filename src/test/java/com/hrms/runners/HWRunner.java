package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = "com/hrms/stepDefinitions",
        tags = {"@hw"},
        plugin = {"pretty",
                "html:target/cucumber-default-reports",
                "json:target/cucumber.json"}
)
public class HWRunner {
}
