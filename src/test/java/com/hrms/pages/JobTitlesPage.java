package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobTitlesPage extends CommonMethods {

    @FindBy(id = "menu_admin_viewAdminModule")
    public WebElement btnAdmin;

    @FindBy(id = "menu_admin_Job")
    public WebElement btnJob;

    @FindBy(id = "menu_admin_viewJobTitleList")
    public WebElement btnJobTitles;

    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr/td[2]")
    public List<WebElement> columnJobTitles;

    public JobTitlesPage() {
        PageFactory.initElements(driver, this);
    }
}
