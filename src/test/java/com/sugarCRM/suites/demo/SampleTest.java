package com.sugarCRM.suites.demo;

import com.TestAutomationDemo.utils.Retry;
import com.aventstack.extentreports.Status;
import com.sugarCRM.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SampleTest extends TestBase {
    @Test(retryAnalyzer = Retry.class)
    public void SampleTest() {
        extentTest = pretest_setup();
        int a = 2+2;
        int b=5;
        if(a==b){
            extentManager.addstep(Status.PASS,"correct",extentTest);
        } else {
            extentManager.addstep(Status.FAIL,"wrong",extentTest);
        }
//        Assert.assertEquals(a==b, "wrong");

    }


}
