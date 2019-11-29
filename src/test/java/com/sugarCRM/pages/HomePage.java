package com.sugarCRM.pages;

import com.TestAutomationDemo.base.ConfigTestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
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
}
