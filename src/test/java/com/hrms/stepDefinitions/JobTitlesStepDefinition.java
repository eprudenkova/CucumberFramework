package com.hrms.stepDefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class JobTitlesStepDefinition extends CommonMethods {

    private List<String> uiJobTitlesList;
    private List<String> dbJobTitlesList;

    @When("navigate to Job Titles Page")
    public void navigate_to_Job_Titles_Page() {
        jsExecutorClick(jobTitlesPage.btnAdmin);
        moveToElementAction(jobTitlesPage.btnJob);
        jsExecutorClick(jobTitlesPage.btnJobTitles);
    }

    @When("collect job titles from UI")
    public void collect_job_titles_from_UI() {
        uiJobTitlesList = new ArrayList<>();
        for (WebElement jobTitle : jobTitlesPage.columnJobTitles) {
            uiJobTitlesList.add(jobTitle.getText());
        }
    }

    @Then("verify job titles data from UI and DB is matched")
    public void verify_job_titles_data_from_UI_and_DB_is_matched() {
        dbJobTitlesList = GlobalVariables.dbList;
        Assert.assertEquals("Job titles data from UI and DB is NOT matched", dbJobTitlesList, uiJobTitlesList);
    }
}

//        1var
//
//        if (uiJobTitlesList.size() > dbJobTitlesList.size()) {
//            int size = uiJobTitlesList.size() - dbJobTitlesList.size();
//            Assert.assertTrue("Job titles data from UI has " + size + " more fields than DB", false);
//        }
//        if (uiJobTitlesList.size() < dbJobTitlesList.size()) {
//            int size = dbJobTitlesList.size() - uiJobTitlesList.size();
//            Assert.assertTrue("Job titles data from DB has " + size + " more fields than UI", false);
//        } else {
//            Assert.assertEquals("Job titles data from UI and DB is NOT matched", dbJobTitlesList, uiJobTitlesList);
//        }
//
//        2 var
//        Iterator<String> iterator = uiExpectedTitles.iterator();
//        while (iterator.hasNext()) {
//            String uiTitle = iterator.next();
//            Iterator<String> iterator1 = dbActualTitles.iterator();
//            while (iterator1.hasNext()) {
//                String dbTitle = iterator1.next();
//                if (uiTitle.equals(dbTitle)) {
//                    iterator.remove();
//                    iterator1.remove();
//                    break;
//                }
//            }
//        }
//        if (uiExpectedTitles.size() > dbActualTitles.size())
//            Assert.assertTrue("Database is missing this titles: " + uiExpectedTitles.toString(), false);
//        if (uiExpectedTitles.size() < dbActualTitles.size())
//            Assert.assertTrue("Data base has this titles which are not displayed in UI: " + dbActualTitles.toString(), false);
//        else
//            Assert.assertEquals("Mismatch data ", uiExpectedTitles, dbActualTitles);
//    }
//}
