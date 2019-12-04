package com.TestAutomationDemo.base;

import com.TestAutomationDemo.constants.GlobalConstants;
import com.TestAutomationDemo.driver.DriverConfig;
import com.TestAutomationDemo.utils.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */

public class ConfigBase {


    public ConfigTestData configTestData=new ConfigTestData();
    public static ExtentManager extentManager=new ExtentManager();
    public ScreenshotGenarator screenshotGenarator;
    public ThreadLocal<RemoteWebDriver> remoteWebDriver = null;
    public Generic generic;
    public HashMap<String,String> testData;

    @BeforeSuite (alwaysRun = true)
    public void beforeSuite(ITestContext context){
        try {
            generic = new Generic(configTestData);
            generic.readConfigProp();

            configTestData.suiteXmlName = context.getSuite().getName();
            String reportName = configTestData.suiteXmlName + "_[" + DateUtils.getDate() +"]_"+ DateUtils.getTime()+".html";
            extentManager.setExtent(reportName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Parameters({"platform","browser"})
    @BeforeMethod(alwaysRun = true)
    public void initSetup(Method testMethod,String platform,@Optional("optional") String browser){
        screenshotGenarator = new ScreenshotGenarator(configTestData);
        configTestData.testPlatform = platform;
        configTestData.testBrowser = browser;
        try {
            configTestData.testName = testMethod.getName();
            remoteWebDriver = new ThreadLocal<>();
            DriverConfig driverConfig = new DriverConfig(configTestData);
            remoteWebDriver.set(driverConfig.getDriver());
            configTestData.driver = remoteWebDriver.get();
        } catch (Exception e){
            e.printStackTrace();
        }
        //Test testClass = testMethod.getAnnotation(Test.class);
        configTestData.groupName = browser;

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        try{
//            enviornmentHashmap = new HashMap<String, String>();
//
//            enviornmentHashmap.put("OS",Generic.getCurretnPlatform().toString());
//            enviornmentHashmap.put("UserName",System.getProperty("user.name"));
//            enviornmentHashmap.put("Platform",configTestData.testPlatform.toUpperCase());
//            enviornmentHashmap.put("Browser",configTestData.testBrowser.toUpperCase());
//            extentManager.setSystemInfo(enviornmentHashmap);
//
//            //extentManager.assignLog(generic.readFile(GlobalConstants.workDir+ Generic.readConfigProp("log.file")));
//            extentManager.setHtmlConfig(configTestData.suiteXmlName);
            extentManager.endReport();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {
        try{
            configTestData.driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
