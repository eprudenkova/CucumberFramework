package com.hrms.utils;

import net.minidev.json.JSONObject;

public class ApiPayloadConstants {

    public static String createEmployeeBody(){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Anna");
        obj.put("emp_lastname", "Black");
        obj.put("emp_middle_name", "W");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "1990-01-01");
        obj.put("emp_status", "Employee");
        obj.put("emp_job_title", "IT Analyst");

        return obj.toString();
    }
}
