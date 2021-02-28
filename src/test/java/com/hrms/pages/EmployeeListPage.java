package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage extends CommonMethods {

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement employeeNameTextBox;

    @FindBy(id = "empsearch_id")
    public WebElement textBoxID;

    @FindBy(id = "searchBtn")
    public WebElement btnSearch;

    @FindBy(xpath = "//table[@id ='resultTable']/tbody/tr[1]/td[2]")
    public WebElement cellID;

    @FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr[1]/td[3]")
    public WebElement cellFirstMiddleName;

    @FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr[1]/td[4]")
    public WebElement cellLastName;

    @FindBy(id="empsearch_job_title")
    public WebElement jobTitleDropDown;

    public EmployeeListPage() {
        PageFactory.initElements(driver, this);
    }
}