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
 * @Date 22-Nov-2019
 */
public class To_Create_a_Lead_in_SugarCRM extends TestBase {


    @Test
    public void To_Create_a_Lead_in_SugarCRM() throws Exception {
       //Variable Declarion
        extentTest = pretest_setup();

        if(testData.get("ExecuteScript").equalsIgnoreCase("YES")) {

            //Step 1
            loginPage.navigate_SugarCRMLoginPage(extentTest);
            loginPage.verify_SugarCRMLoginPage_isDisplayed(extentTest);

            //Step 2
            loginPage.user_Login_To_SugarCRM(testData,extentTest);
            homePage.verifyAuthHomePage(extentTest);

            //Step 3
            homePage.user_clicks_on_Leads_tab(extentTest);

            //Step 4
            leadsPage.user_click_on_CreateLead(extentTest);
            leadsPage.user_fills_Lead_information(testData,extentTest);

            //Step 5
            leadsPage.user_fills_the_address_information(testData,extentTest);

            //Step 6
            leadsPage.user_fills_the_email_details(testData,extentTest);

            //Step 7
            leadsPage.user_clicks_on_save_button(extentTest);

            //Step 8
            leadsPage.user_verifies_the_creation_of_lead(extentTest);

            //Step 9
            leadsPage.user_logsout_from_application(extentTest);
            Thread.sleep(2000);
            Verification.verifyPageUrl(loginPage.login_URL);

        }
    }

}
