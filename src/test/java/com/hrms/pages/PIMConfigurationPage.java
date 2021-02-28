package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMConfigurationPage extends CommonMethods {

    @FindBy(id = "menu_pim_Configuration")
    public WebElement configurationDD;

    @FindBy(id = "menu_pim_configurePim")
    public WebElement optionalFields;

    @FindBy(xpath = "//input[@value='Edit']")
    public WebElement btnEdit;

    @FindBy(xpath = "//li[@class='checkbox']")
    public List<WebElement> checkBoxes;

    public void clickOnConfigurationDD(){
        jsExecutorClick(configurationDD);
    }

    public void clickOnOptionalFields(){
        jsExecutorClick(optionalFields);
    }

    public void clickOnEditBtn(){
        jsExecutorClick(btnEdit);
    }

    public PIMConfigurationPage() {
        PageFactory.initElements(driver, this);
    }

}
