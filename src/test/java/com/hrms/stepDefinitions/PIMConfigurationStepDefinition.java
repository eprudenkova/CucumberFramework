package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.List;

public class PIMConfigurationStepDefinition extends CommonMethods {

    @When("click on PIM")
    public void click_on_PIM() {
        dashboardPage.clickOnPIMBtn();
    }

    @When("click on Configuration drop down")
    public void click_on_Configuration_drop_down() {
        pimConfigurationPage.clickOnConfigurationDD();
    }

    @When("click on Optional Fields")
    public void click_on_Optional_Fields() {
        pimConfigurationPage.clickOnOptionalFields();
    }

    @When("click on Edit button")
    public void click_on_Edit_button() {
        pimConfigurationPage.clickOnEditBtn();
    }

    @When("uncheck unnecessary checkboxes")
    public void uncheck_unnecessary_checkboxes(DataTable checkBoxOptions) throws InterruptedException {
        List<String> expectedCheckBoxOptions = checkBoxOptions.asList();
        for (int i = 0; i < expectedCheckBoxOptions.size(); i++) {
            clickRadioOrCheckBoxByText(pimConfigurationPage.checkBoxes, expectedCheckBoxOptions.get(i));
        }
        Thread.sleep(5000);
    }

}
