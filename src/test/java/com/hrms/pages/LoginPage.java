package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    @FindBy(id = "txtUsername")
    public WebElement username;

    @FindBy(name = "txtPassword")
    public WebElement password;

    @FindBy(css = "input#btnLogin")
    public WebElement loginBtn;

    @FindBy(xpath = "//div[@id='divLogo']/img")
    public WebElement logo;

    @FindBy(id = "spanMessage")
    public WebElement errorMsg;

    public LoginPage() {
        PageFactory.initElements(driver, this);

    }

    public void login(String uname, String pwd) {
        sendText(username, uname);
        sendText(password, pwd);
//        click(loginBtn);
    }

    public void clickOnLoginBtn(){
        click(loginBtn);
    }

    public String getErrorMessageText(){
        return errorMsg.getText();
    }
}
