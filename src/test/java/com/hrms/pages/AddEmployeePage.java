package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id = "firstName")
    public WebElement firstNameTextBox;
    @FindBy(id = "middleName")
    public WebElement middleNameTextBox;
    @FindBy(id = "lastName")
    public WebElement lastNameTextBox;
    @FindBy(id = "employeeId")
    public WebElement empIDTextBox;
    @FindBy(id = "photofile")
    public WebElement chooseFileBtn;
    @FindBy(id = "chkLogin")
    public WebElement createLoginCheckbox;
    @FindBy(id = "user_name")
    public WebElement usernameTextBox;
    @FindBy(id = "user_password")
    public WebElement passwordTextBox;
    @FindBy(id = "re_password")
    public WebElement confirmPasswordTextBox;
    @FindBy(id = "btnSave")
    public WebElement saveBtn;

    public AddEmployeePage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }

    public void enterFirstMiddleLastName(String firstname, String middlename, String lastname){
        sendText(firstNameTextBox,firstname);
        sendText(middleNameTextBox,middlename);
        sendText(lastNameTextBox,lastname);
    }

    public void enterEmployeeID(String employeeID){
        sendText(empIDTextBox,employeeID);
    }
}
