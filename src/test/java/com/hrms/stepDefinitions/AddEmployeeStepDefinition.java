package com.hrms.stepDefinitions;

import com.hrms.utils.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.*;

public class AddEmployeeStepDefinition extends CommonMethods {

    private String actualID;
    private String expectedID;
    private String expectedEmployeeData;
    private String actualEmployeeData;

    @When("navigate to Add Employee page")
    public void navigate_to_Add_Employee_page() {
        jsExecutorClick(dashboardPage.btnPIM);
        jsExecutorClick(dashboardPage.btnAddEmp);
    }

    @When("enter First Name {string} and Last Name {string}")
    public void enter_First_Name_and_Last_Name(String firstname, String lastname) {
        sendText(addEmployeePage.firstNameTextBox, firstname);
        sendText(addEmployeePage.lastNameTextBox, lastname);
    }

    @When("see what ID number was created")
    public void see_what_ID_number_was_created() {
        actualID = addEmployeePage.empIDTextBox.getAttribute("value");
    }

    @When("click on save button")
    public void click_on_save_button() {
        click(addEmployeePage.saveBtn);
    }

    @Then("verify ID is displayed on Personal Details page")
    public void verify_ID_is_displayed_on_Personal_Details_page() {
        expectedID = personalDetailsPage.employeeIDTextBox.getAttribute("value");
        Assert.assertEquals("Verifying ID", expectedID, actualID);
    }

    @When("click on Create Login Details checkbox")
    public void click_on_Create_Login_Details_checkbox() {
        click(addEmployeePage.createLoginCheckbox);
    }

    @When("enter User Name {string} and Password {string} and Confirm Password {string}")
    public void enter_User_Name_and_Password_and_Confirm_Password(String username, String password1, String password2) {
        sendText(addEmployeePage.usernameTextBox, username);
        sendText(addEmployeePage.passwordTextBox, password1);
        sendText(addEmployeePage.confirmPasswordTextBox, password2);
    }

    @When("enter First Name {string} and Middle Name {string} and Last Name {string}")
    public void enter_First_Name_and_Middle_Name_and_Last_Name(String firstname, String middlename, String lastname) {
        addEmployeePage.enterFirstMiddleLastName(firstname, middlename, lastname);

        GlobalVariables.employeeData = firstname + middlename + lastname;
    }

    @Then("verify that {string} is added successfully")
    public void verify_that_is_added_successfully(String fullName) {
        String actualFullName = personalDetailsPage.getUserProfileName();
        Assert.assertEquals("Verifying Full Name", fullName, actualFullName);
    }

    @When("enter {string},{string} and {string}")
    public void enter_and(String firstname, String middlename, String lastname) {
        addEmployeePage.enterFirstMiddleLastName(firstname, middlename, lastname);
    }

    @Then("verify {string}, {string} and {string} is added successfully")
    public void verify_and_is_added_successfully(String firstname, String middlename, String lastname) {
        String fullName = firstname + " " + middlename + " " + lastname;
        String actualFullName = personalDetailsPage.getUserProfileName();
        Assert.assertEquals("Verifying Full Name", fullName, actualFullName);
    }

    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) throws InterruptedException {
        List<Map<String, String>> employeeNames = employees.asMaps();

        for (Map<String, String> employeeName : employeeNames) {
            String firstName = employeeName.get("FirstName");
            String middleName = employeeName.get("MiddleName");
            String lastName = employeeName.get("LastName");
            String employeeID = employeeName.get("EmployeeID");

            addEmployeePage.enterFirstMiddleLastName(firstName, middleName, lastName);
            addEmployeePage.enterEmployeeID(employeeID);
            jsExecutorClick(addEmployeePage.saveBtn);
            String actualFullName = personalDetailsPage.getUserProfileName();
            String expectedFullName = firstName + " " + middleName + " " + lastName;
            Assert.assertEquals("Verifying Full Name", expectedFullName, actualFullName);
            jsExecutorClick(dashboardPage.btnPIM);
            jsExecutorClick(dashboardPage.btnAddEmp);
            Thread.sleep(1000);
        }
    }

    @When("add multiple employees from excel {string} sheet and verify they are added")
    public void add_multiple_employees_from_excel_sheet_and_verify_they_are_added(String sheetName) {
        List<Map<String, String>> excelData = ExcelReader.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetName);

        for (Map<String, String> excelEmpName : excelData) {
            String firstName = excelEmpName.get("FirstName");
            String middleName = excelEmpName.get("MiddleName");
            String lastName = excelEmpName.get("LastName");
            String employeeID = excelEmpName.get("EmployeeID");

            addEmployeePage.enterFirstMiddleLastName(firstName, middleName, lastName);
            addEmployeePage.enterEmployeeID(employeeID);
            jsExecutorClick(addEmployeePage.saveBtn);
            String actualFullName = personalDetailsPage.getUserProfileName();
            String expectedFullName = firstName + " " + middleName + " " + lastName;
            Assert.assertEquals("Verifying Full Name", expectedFullName, actualFullName);
            jsExecutorClick(dashboardPage.btnPIM);
            jsExecutorClick(dashboardPage.btnAddEmp);

        }
    }

    @When("capture employeeID")
    public void capture_employeeID() {
        GlobalVariables.employeeID = addEmployeePage.empIDTextBox.getAttribute("value");
    }


    @Then("verify data from DB and UI is matched")
    public void verify_data_from_DB_and_UI_is_matched() {
        expectedEmployeeData = GlobalVariables.employeeData;

        actualEmployeeData = "";
        for (Map<String, String> actualEmployeeDataMap : GlobalVariables.dbListOfMaps) {
            Set<String> keys = actualEmployeeDataMap.keySet();
            for (String key : keys) {
                actualEmployeeData += actualEmployeeDataMap.get(key);
            }
        }
        actualEmployeeData = actualEmployeeData.trim();

        Assert.assertEquals("Verifying Employee Data", actualEmployeeData, expectedEmployeeData);

    }
}
