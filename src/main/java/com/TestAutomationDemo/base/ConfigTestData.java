package com.TestAutomationDemo.base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */

public class ConfigTestData {

    // Remote we driver
    public RemoteWebDriver driver = null;

    public String testPlatform = null;
    public String testBrowser = null;

    public String groupName = null;
    public String suiteXmlName=null;

    public String testMethodName = null;

    public int stepNo =  0;
    public String stepDescription =  null;
    public String stepExpected =  null;
    public String stepFail = null;

    public Status finalTestCaseStatus = Status.PASS;



}
