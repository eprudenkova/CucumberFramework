package com.hrms.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ApiPayloadConstants {

    public static String createEmployeeBody() {

//        JSONObject obj = new JSONObject();
//        obj.put("emp_firstname", "Anna");
//        obj.put("emp_lastname", "Black");
//        obj.put("emp_middle_name", "W");
//        obj.put("emp_gender", "F");
//        obj.put("emp_birthday", "1990-01-01");
//        obj.put("emp_status", "Employee");
//        obj.put("emp_job_title", "IT Analyst");
//
//        return obj.toString();

        File input = new File("/Users/evgeniya/IdeaProjects/CucumberFramework/src/test/resources/JsonData/createUser.json");

        JsonObject createUserData = null;
        try {
            //parsing the input file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));//parseReader() - json text reader, returns JsonElement
            createUserData = fileElement.getAsJsonObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return createUserData.toString();
    }
}

