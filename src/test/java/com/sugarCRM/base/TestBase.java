package com.sugarCRM.base;

import com.TestAutomationDemo.base.ConfigBase;
import com.TestAutomationDemo.constants.GlobalConstants;
import com.TestAutomationDemo.keywords.Action;
import com.TestAutomationDemo.keywords.Verification;
import com.TestAutomationDemo.utils.Generic;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sugarCRM.constatns.ProjectConstants;
import com.sugarCRM.generic.ProjectGeneric;
import com.sugarCRM.pages.HomePage;
import com.sugarCRM.pages.LeadsPage;
import com.sugarCRM.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */
public class TestBase extends ConfigBase {
    // Variable declarions
    public ExtentTest extentTest;
    public Action action;
    public Verification verification;
    public ProjectGeneric projectGeneric;



    public LoginPage loginPage;
    public HomePage homePage;
    public LeadsPage leadsPage;




    public void initPageClass(){
        action = new Action(configTestData);
        verification = new Verification(configTestData);
        projectGeneric = new ProjectGeneric();


        homePage = new HomePage(configTestData);
        loginPage=new LoginPage(configTestData);
        leadsPage = new LeadsPage(configTestData);
    }

    public ExtentTest pretest_setup(){
        initPageClass();
        ExtentTest test=null;
        testData = projectGeneric.getTestData(GlobalConstants.TEST_DATA_PATH, ProjectConstants.TEST_DATA_SUGARCRM_LEAD, configTestData.testName);
        configTestData.executeScript = testData.get("ExecuteScript");
        if(configTestData.executeScript.equalsIgnoreCase("YES")) {
            test = extentManager.createTest(configTestData.testName);
            extentManager.assignGroup(configTestData.testBrowser, test);
        }
        return test;
    }


   public void report(Status status,String description,ExtentTest extentTest){
        extentManager.addstep(status,description,extentTest);
   }


}
