package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/hrms/stepDefinitions",
        tags = "@smoke",
        dryRun = false,
        plugin = {"pretty"})

public class GoogleRunner {
}
