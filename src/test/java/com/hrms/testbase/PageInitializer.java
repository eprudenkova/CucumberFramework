package com.hrms.testbase;

import com.hrms.pages.*;

public class PageInitializer extends BaseClass {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeListPage employeeListPage;
    public static PersonalDetailsPage personalDetailsPage;
    public static PIMConfigurationPage pimConfigurationPage;
    public static JobTitlesPage jobTitlesPage;

    public static void initializePageObjects() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        addEmployeePage = new AddEmployeePage();
        employeeListPage = new EmployeeListPage();
        personalDetailsPage = new PersonalDetailsPage();
        pimConfigurationPage = new PIMConfigurationPage();
        jobTitlesPage = new JobTitlesPage();
    }
}
