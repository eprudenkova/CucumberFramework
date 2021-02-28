package com.hrms.stepDefinitions;

import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;
import io.cucumber.java.en.When;

public class DBStepDefinition {

    @When("collect employee data from hrms database")
    public void collect_employee_data_from_hrms_database() {

        String query = "select emp_firstname, emp_middle_name, emp_lastname " +
                "from hs_hr_employees where employee_id = " + GlobalVariables.employeeID;
        GlobalVariables.dbListOfMaps = DBUtils.getDBDataIntoListOfMaps(query);
    }

    @When("collect job titles from hrms DB")
    public void collect_job_titles_from_hrms_DB() {

        String query = "select job_title from ohrm_job_title order by job_title asc;";
        GlobalVariables.dbList = DBUtils.getDBDataIntoList(query);
    }
}
