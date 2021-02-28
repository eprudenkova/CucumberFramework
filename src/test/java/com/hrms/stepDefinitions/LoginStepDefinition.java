package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefinition extends CommonMethods {

    @Given("navigate to HRMS login page")
    public void navigate_to_hrms_login_page() {
        setUp();
    }

    @When("enter valid credentials")
    public void enter_valid_credentials() {
        sendText(loginPage.username, ConfigsReader.getPropertyValue("username"));
        sendText(loginPage.password, ConfigsReader.getPropertyValue("password"));
//        loginPage.login("Admin", "Hum@nhrm123");
    }

    @When("click on login button")
    public void click_on_login_button() {
        click(loginPage.loginBtn);
//        loginPage.clickOnLoginBtn();
    }

    @Then("verify dashboard is displayed")
    public void verify_dashboard_is_displayer() {
        Assert.assertTrue(dashboardPage.welcome.isDisplayed());
    }

    @Then("quit the browser")
    public void quit_the_browser() {
        tearDown();
    }

    @When("enter invalid credentials")
    public void enter_invalid_credentials() {
        sendText(loginPage.username, "Admin");
        sendText(loginPage.password, "wrong password");
    }

    @Then("verify error message")
    public void verify_error_message() {
        Assert.assertEquals("Verifying error message","Invalid credentials",loginPage.getErrorMessageText());
    }

    @When("leave blank username")
    public void leave_blank_username() {
       sendText(loginPage.username,"");
    }

    @When("enter valid password")
    public void enter_valid_password() {
        sendText(loginPage.password,ConfigsReader.getPropertyValue("password"));
    }
    @When("enter valid username")
    public void enter_valid_username() {
       sendText(loginPage.username,ConfigsReader.getPropertyValue("username"));
    }

    @When("leave blank password")
    public void leave_blank_password() {
       sendText(loginPage.password,"");
    }

    @Then("verify error message for empty password")
    public void verify_error_message_for_empty_password() {
        Assert.assertEquals("Verifying error message for empty password","Password cannot be empty",loginPage.getErrorMessageText());
    }

    @Then("verify error message for empty username")
    public void verify_error_message_for_empty_username() {
        Assert.assertEquals("Verifying error message for empty username","Username cannot be empty",loginPage.getErrorMessageText());
    }

}
