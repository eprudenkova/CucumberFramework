package com.hrms.stepDefinitions;

import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.DBUtils;

import java.util.List;
import java.util.Map;

public class FunctionTest{

    public static void main(String[] args) {

        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        String query = "select emp_firstname, emp_lastname from hs_hr_employees";

        List<Map<String, String>> list = DBUtils.getDBDataIntoListOfMaps(query);

        System.out.println(list);
        System.out.println(list.size());


    }
}
//US: added employee data should be stored properly in database
//Scenario
//add employee
//capture our employee id
//click on save
//