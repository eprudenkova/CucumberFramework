package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(// configuration for our tests
        features = "src/test/resources/features",// need to give a path for our feature file
        glue = "com/hrms/api",// trying to connect all our implementation code for Gherkin steps
        dryRun = false, //when set as true, will run over the feature steps and show unimplemented steps in console
        tags = {"@apiWorkflow"}, //adding tag
        strict = false, //when set as true, it will fail the execution when undefined step is found.
        plugin = {"pretty", //prints Gherkin steps inside your console
                "html:target/cucumber-default-reports", //generates default html report
                "rerun:target/FailedTests.txt", //generates a txt file with failed tests only
                "json:target/cucumber.json" //generates json report
        })

public class ApiRunner {}
