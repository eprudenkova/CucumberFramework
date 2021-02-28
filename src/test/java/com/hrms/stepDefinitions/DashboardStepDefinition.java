package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class DashboardStepDefinition extends CommonMethods {

    @Then("verify the following tabs on dashboard")
    public void verify_the_following_tabs_on_dashboard(DataTable dashboardTabs) {
        List<String> expectedDashTabs = dashboardTabs.asList();
        List<String> actualDashTabs = dashboardPage.getDashTabs();

        System.out.println("Expected: "+expectedDashTabs);
        System.out.println("Actual: "+actualDashTabs);

        Assert.assertEquals("Verifying", expectedDashTabs, actualDashTabs);
    }
}
