package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(id = "menu_admin_viewAdminModule")
    public WebElement btnAdmin;

    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement btnPIM;

    @FindBy(id = "welcome")
    public WebElement welcome;

    @FindBy(xpath = "//div[@id='branding']/a[1]")
    public WebElement logo;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement btnAddEmp;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement btnEmployeeList;

    @FindBy(xpath = "//div[@class = 'menu']/ul/li")
    public List<WebElement> dashTabs;

    public void clickOnPIMBtn(){
        jsExecutorClick(btnPIM);
    }

    public List<String> getDashTabs() {
        List<String> dashTabText = new ArrayList<>();
        for (WebElement dashTab : dashTabs) {
            dashTabText.add(dashTab.getText());
        }
        return dashTabText;
    }

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
