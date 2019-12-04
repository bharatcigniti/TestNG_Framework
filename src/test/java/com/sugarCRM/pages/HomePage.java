package com.sugarCRM.pages;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.keywords.Action;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.sugarCRM.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    WebDriver driver;
    ConfigTestData configTestData;


    public HomePage(ConfigTestData configTestData) {
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
        PageFactory.initElements(this.driver,this);
    }


    @CacheLookup
    @FindBy(xpath = "//a[text()='Leads']")
    public WebElement tab_Leads;

    public void user_clicks_on_Leads_tab(ExtentTest extentTest){
        boolean status=false;
        try{
            Action.click(tab_Leads);
            Thread.sleep(5000);
            report(Status.PASS,"Leads page is displayed",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }
    public boolean verifyAuthHomePage(ExtentTest extentTest){
        boolean status=false;
        try{
            status= tab_Leads.isDisplayed();
            if(status) {
                report(Status.PASS,"Authinticated Home page is displayed",extentTest);
            } else{
                report(Status.FAIL,"Authinticated Home page does not displayed",extentTest);
            }
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
        return status;
    }
}
