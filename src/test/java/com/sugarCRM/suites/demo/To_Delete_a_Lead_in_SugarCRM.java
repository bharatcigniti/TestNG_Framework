package com.sugarCRM.suites.demo;

import com.TestAutomationDemo.constants.GlobalConstants;
import com.TestAutomationDemo.keywords.Verification;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sugarCRM.base.TestBase;
import com.sugarCRM.constatns.ProjectConstants;
import org.testng.annotations.Test;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 25-Nov-2019
 */
public class To_Delete_a_Lead_in_SugarCRM extends TestBase {


    @Test
    public void To_Delete_a_Lead_in_SugarCRM() throws Exception {
       //Variable Declarion
        extentTest = pretest_setup();

        if(testData.get("ExecuteScript").equalsIgnoreCase("YES")) {

            //Step 1
            loginPage.navigate_SugarCRMLoginPage(extentTest);
            loginPage.verify_SugarCRMLoginPage_isDisplayed(extentTest);

            //Step 2
            loginPage.user_Login_To_SugarCRM(testData,extentTest);
            //homePage.verifyAuthHomePage(extentTest);

            //Step 3
            Thread.sleep(1000);
            homePage.user_clicks_on_Leads_tab(extentTest);

            // Step 4
            leadsPage.user_enter_lead_fname_lname(testData.get("First_Name"), testData.get("Last_Name"),extentTest);

            // Step 5
            leadsPage.user_delete_lead_information(extentTest);

            //Step 6
            leadsPage.user_logsout_from_application(extentTest);
            Thread.sleep(2000);
            Verification.verifyPageUrl(loginPage.login_URL);

        }
    }

}
