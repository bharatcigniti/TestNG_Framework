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
public class To_Edit_a_Lead_in_SugarCRM extends TestBase {

    @Test(groups={"Smoke"})
    public void To_Edit_a_Lead_in_SugarCRM() throws Exception {
       //Variable Declarion
        configTestData.testMethodName = "To_Edit_a_Lead_in_SugarCRM";
        testData = generic.getTestData(GlobalConstants.TEST_DATA_PATH, ProjectConstants.TEST_DATA_SUGARCRM_LEAD, configTestData.testMethodName);

        if(testData.get("Execute").equalsIgnoreCase("YES")) {
            ExtentTest test;
            test = extentManager.createTest(configTestData.testMethodName);
            extentManager.createNode("Browser  ::  " + configTestData.testBrowser, configTestData.testMethodName, test);
            extentManager.assignGroup(configTestData.testBrowser, test);
            initPageClass();

            //Step 1
            loginPage.navigate_SugarCRMLoginPage();
            verify(loginPage.verify_SugarCRMLoginPage_isDisplayed(),"user navigates to homepage",test);

            //Step 2
            loginPage.user_Login_To_SugarCRM(testData);
            verify(homePage.tab_Leads.isDisplayed(),"User enters credentials and cliks login",test);

            //Step 3
            homePage.tab_Leads.click();
            Thread.sleep(15);
            verify(leadsPage.header_Leads.isDisplayed(),"user clicks on Leads tab. Leads Tab displayed",test);

            //Step 4
            leadsPage.user_edit_lead_information(testData);
            action("User edits the lead information and cliks on save button",test);
            Thread.sleep(500);

            //Step 5
            leadsPage.user_logsout_from_application();
            Thread.sleep(2000);
            verify(Verification.verifyPageUrl(loginPage.login_URL),"user logsout from application",test);


        } else {
            configTestData.finalTestCaseStatus = Status.SKIP;
        }
    }

}
