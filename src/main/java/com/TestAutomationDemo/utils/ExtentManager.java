package com.TestAutomationDemo.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.constants.GlobalConstants;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public WebDriver driver;
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;


    public void setExtent(String reportname) {
        // specify location of the report
       String reportPath = GlobalConstants.workDir + Generic.readConfigProp("reports.path") + reportname;
        htmlReporter = new ExtentHtmlReporter(reportPath);

        htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Functional Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setReportUsesManualConfiguration(true);

        // Passing General information
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environemnt", "QA");
        extentReports.setSystemInfo("user", "Bharath");
    }

    public ExtentTest createTest(String testName){
        ExtentTest extentTest = extentReports.createTest(testName);
        return extentTest;
    }
    public void createNode(String name,String description,ExtentTest test){
        test.createNode(name,description);

    }
    public void addstep(Status status,String details,ExtentTest test){

        try {
            switch (status) {
                case INFO:
                    test.log(Status.INFO,details);
                    break;
                case PASS:
                    test.pass(details);
                    Assert.assertEquals(true, details);
                    break;
                case FAIL:
                    test.fail(details);
                    Assert.assertEquals(false, details);
                    break;
                case SKIP:
                    test.skip(details);

                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void assignGroup(String group,ExtentTest extentTest){
        extentTest.assignCategory(group);
    }

    public void endReport() {
        extentReports.flush();
    }

    public ExtentTest getExtentTest(String testName,String execute){
        ExtentTest extentTest=null;
        if(execute.equalsIgnoreCase("YES")) {
            extentTest = createTest(testName);
        } else{
            extentTest = createTest(testName).skip("Test Script :: "+testName+"  Skipped");
        }
        return extentTest;
    }
}