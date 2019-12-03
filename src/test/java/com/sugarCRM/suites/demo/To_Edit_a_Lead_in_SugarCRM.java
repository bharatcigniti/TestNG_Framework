package com.sugarCRM.suites.demo;

import com.TestAutomationDemo.constants.GlobalConstants;
import com.TestAutomationDemo.keywords.Verification;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sugarCRM.base.TestBase;
import com.sugarCRM.constatns.ProjectConstants;
import org.testng.annotations.Test;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 25-Nov-2019
 */
public class To_Edit_a_Lead_in_SugarCRM extends TestBase {


    @Test
    public void To_Edit_a_Lead_in_SugarCRM() throws Exception {
       //Variable Declarion
        extentTest = pretest_setup();

        if(testData.get("ExecuteScript").equalsIgnoreCase("YES")) {
            //Step 1
            loginPage.navigate_SugarCRMLoginPage(extentTest);
            verify(loginPage.verify_SugarCRMLoginPage_isDisplayed(extentTest), "user navigates to homepage", extentTest);

            //Step 2
            loginPage.user_Login_To_SugarCRM(testData,extentTest);
            verify(homePage.tab_Leads.isDisplayed(), "User enters credentials and cliks login", extentTest);

            //Step 3
            homePage.tab_Leads.click();
            Thread.sleep(15);
            verify(leadsPage.header_Leads.isDisplayed(), "user clicks on Leads tab. Leads Tab displayed", extentTest);

            //Step 4
            leadsPage.user_edit_lead_information(testData,extentTest);
            action("User edits the lead information and cliks on save button", extentTest);
            Thread.sleep(500);

            //Step 5
            leadsPage.user_logsout_from_application(extentTest);
            Thread.sleep(2000);
            verify(Verification.verifyPageUrl(loginPage.login_URL), "user logsout from application", extentTest);

        }
    }

}
