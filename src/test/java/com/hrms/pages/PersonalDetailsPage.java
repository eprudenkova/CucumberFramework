package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPage extends CommonMethods {
    @FindBy(id = "personal_txtEmpFirstName")
    public WebElement firstNamTextBox;

    @FindBy(id = "personal_txtEmpLastName")
    public WebElement lastNameTextBox;

    @FindBy(id = "personal_txtEmployeeId")
    public WebElement employeeIDTextBox;

    @FindBy(xpath = "//div[@id = 'profile-pic']//following-sibling::h1")
    public WebElement userProfileName;

    public PersonalDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getUserProfileName(){
        return userProfileName.getText();
    }
}
