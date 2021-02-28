package com.hrms.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchGoogleStepDefinition {

    WebDriver driver;

    @Given("navigate to google application")
    public void navigate_to_google_application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
    }

    @When("user enters any text")
    public void user_enters_any_text() {
        driver.findElement(By.name("q")).sendKeys("Maven Repository");
    }

    @When("click hit enter")
    public void click_hit_enter() {
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);//hit enter
    }

    @Then("verify the result is displayed")
    public void verify_the_result_is_displayed() {
        boolean isDisplayed = driver.findElement(By.xpath("//link[@href='https://mvnrepository.com/']")).isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @When("click on google search button")
    public void click_on_google_search_button() {
//        driver.findElement(By.xpath("//input[@data-ved='0ahUKEwiwlsivs67uAhVlvlkKHamWAO4Q4dUDCAk']")).click();
        driver.findElement(By.id("gsr")).click();

    }
}
