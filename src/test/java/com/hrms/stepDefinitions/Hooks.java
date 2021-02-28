package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends CommonMethods{//- PageInitializer, BaseClass

    @Before
    public void startTest() {
        setUp();
    }

    @After
    public void endTest(Scenario scenario) {
        byte[] screenshot;
        if (scenario.isFailed()) {
            screenshot = CommonMethods.takeScreenshot("failed/" + scenario.getName());
        } else {
            screenshot = CommonMethods.takeScreenshot("passed/" + scenario.getName());
        }

        scenario.attach(screenshot, "image/png", scenario.getName());

        tearDown();
    }
}
