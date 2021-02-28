package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.util.List;

public class EmployeeListStepDefinition extends CommonMethods {

    private List<String> uiJobTitlesList;
    private List<String> dbJobTitlesList;

    @When("navigate to Employee List page")
    public void navigate_to_Employee_List_page() throws InterruptedException {
        jsExecutorClick(dashboardPage.btnPIM);
        jsExecutorClick(dashboardPage.btnEmployeeList);
        Thread.sleep(1000);
    }

    @When("enter employee ID {string}")
    public void enter_employee_ID(String employeeID) {
        sendText(employeeListPage.textBoxID, employeeID);
    }

    @When("click on Search button")
    public void click_on_Search_button() {
        jsExecutorClick(employeeListPage.btnSearch);
    }

    @Then("verify employee ID {string} is displayed")
    public void verify_employee_ID_is_displayed(String expectedID) {
        String actualID = employeeListPage.cellID.getText();
        Assert.assertEquals("Verifying by ID", expectedID, actualID);
    }

    @When("enter employee full name {string}")
    public void enter_employee_full_name(String fullName) {
        sendText(employeeListPage.employeeNameTextBox, fullName);

    }

    @Then("verify employee full name {string} is displayed")
    public void verify_employee_full_name_is_displayed(String expectedFullName) {
        String actualFirstMiddleName = employeeListPage.cellFirstMiddleName.getText();
        String actualLastName = employeeListPage.cellLastName.getText();
        String actualFullName = actualFirstMiddleName + " " + actualLastName;
        Assert.assertEquals("Verifying by Full Name", expectedFullName, actualFullName);
    }

    @When("collect job titles from UI Employee List Job Title DropDown")
    public void collect_job_titles_from_UI_Employee_List_Job_Title_DropDown() {
        uiJobTitlesList = getAllDataFromDropDownIntoListOfString(employeeListPage.jobTitleDropDown);
    }

    @Then("verify job titles data from UI Employee List Job Title DropDown and DB is matched")
    public void verify_job_titles_data_from_UI_Employee_List_Job_Title_DropDown_and_DB_is_matched() {
        dbJobTitlesList= GlobalVariables.dbList;
        Assert.assertEquals("Job titles data from UI Employee List Job Title DropDown and DB is NOT matched",
                dbJobTitlesList, uiJobTitlesList);
    }
}
