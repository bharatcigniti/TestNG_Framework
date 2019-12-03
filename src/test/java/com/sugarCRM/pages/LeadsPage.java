package com.sugarCRM.pages;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.constants.GlobalConstants;
import com.TestAutomationDemo.keywords.Action;
import com.TestAutomationDemo.keywords.Verification;
import com.TestAutomationDemo.utils.Generic;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sugarCRM.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class LeadsPage extends TestBase {
    WebDriver driver;
    ConfigTestData configTestData;
    //TestBase testData;


    public LeadsPage(ConfigTestData configTestData) {
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
        // this.testData = new TestBase();
        //  testData.loadTestData();
        PageFactory.initElements(this.driver,this);

    }

    @CacheLookup
    @FindBy(xpath = "//li[@id='0_lv']//a[contains(text(),'Create Lead')]")
    public WebElement lnk_CreateLeads;

    @CacheLookup
    @FindBy(xpath = "//h2[contains(text(),'Leads:')]")
    public WebElement header_Leads;

    @CacheLookup
    @FindBy(id = "phone_work")
    public WebElement input_OfficePhone;

    @CacheLookup
    @FindBy(id = "phone_home")
    public WebElement input_HomePhone;

    @CacheLookup
    @FindBy(id = "phone_mobile")
    public WebElement input_MobilePhone;

    @CacheLookup
    @FindBy(id = "last_name")
    public WebElement input_LastName;

    @CacheLookup
    @FindBy(xpath="//select[@name='salutation']")
    public WebElement list_Salutation;

    @CacheLookup
    @FindBy(name = "first_name")
    public WebElement input_FirstName;

    @CacheLookup
    @FindBy(id="lead_source")
    public WebElement list_Leadsource;

    @CacheLookup
    @FindBy(xpath="//input[@name='btn_campaign_name']")
    public WebElement btn_Campaign_Select;

    @FindBy(xpath="//input[@id='name_advanced']")
    public WebElement campaign_SearchName;

    @FindBy(xpath="//input[@title='Search [Alt+Q]']")
    public WebElement campaign_SearchButton;

    @FindBy(xpath="//td[@class='oddListRowS1']//a[1]")
    public WebElement campaign_SearchResult;

    @FindBy(xpath="//input[@name='account_name']")
    public WebElement account_name;

    @FindBy(xpath="//input[@name='title']")
    public WebElement title;

    @FindBy(id="department")
    public WebElement department;

    @FindBy(xpath="//input[@name='btn_assigned_user_name']")
    public WebElement assigned_select;

    @FindBy(xpath="//input[@name='first_name']")
    public WebElement assignedto_fname;

    @FindBy(xpath="//input[@name='last_name']")
    public WebElement assignedto_lname;

    @FindBy(xpath="//input[@name='user_name']")
    public WebElement assignedto_uname;

    @FindBy(xpath="//input[@title='Cancel [Alt+X]']")
    public WebElement assignedto_cancel;

    @FindBy(id="primary_address_street")
    public WebElement primary_address;

    @FindBy(id="primary_address_city")
    public WebElement city;

    @FindBy(id="primary_address_state")
    public WebElement state;


    @FindBy(id="primary_address_postalcode")
    public WebElement postalcode;

    @FindBy(id="primary_address_country")
    public WebElement country;

    @FindBy(id="alt_checkbox")
    public WebElement copy_checkbox;

    @FindBy(xpath="//div[@id='LBL_DESCRIPTION_INFORMATION']/following-sibling::div[1]//input[1]")
    public WebElement Save;

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    public WebElement logout;

    @FindBy(xpath="//td[@valign='top']//h2")
    public WebElement lead_verification;

    @FindBy(xpath="//a[contains(text(),'Add Address')]")
    public WebElement add_address;

    @FindBy(xpath="//img[@id='removeButton1']")
    public WebElement email_delete;

    @FindBy(xpath="//input[@name='emailAddress0']")
    public WebElement email_field1;

    @FindBy(xpath="//input[@name='emailAddress1']")
    public WebElement email_field2;

    //Edit Lead

    @FindBy(xpath="//input[@id='first_name_basic']")
    public WebElement edit_fname;

    @FindBy(xpath="//input[@id='last_name_basic']")
    public WebElement edit_lname;

    @FindBy(xpath="//input[@id='search_form_submit']")
    public WebElement edit_Search;

    @FindBy(xpath="//td[@id='main']//tr[3]//td[7]//a[1]")
    public WebElement edit_click;

    @FindBy(xpath="//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath="//body/table/tbody/tr/td/form/table/tbody/tr[3]/td[1]/input[1]")
    public WebElement delete_checkbox;

    @FindBy(xpath="//form[@name='DetailView']/input[@value='Delete']")
    public WebElement delete;

    @FindBy(xpath="//form[@id='MassUpdate']/table")
    public WebElement tblMassData;

    @FindBy(id="edit_button")
    public WebElement btnEdit;


    public void user_click_on_CreateLead(ExtentTest extentTest){
        try {
            action.click(lnk_CreateLeads);
            report(Status.PASS,"User click on CreateLead",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public void user_fills_Lead_information(HashMap<String,String> testData,ExtentTest extentTest){
        try {
            Thread.sleep(30);
            action.enterInput(input_OfficePhone, testData.get("Office_Phone"));
            action.enterInput(input_HomePhone, testData.get("Home_Phone"));
            action.enterInput(input_MobilePhone, testData.get("Mobile_Phone"));
            action.enterInput(input_LastName, testData.get("Last_Name"));

            action.selectItemInCombobox(list_Salutation, testData.get("Salutation"));
            action.enterInput(input_FirstName, testData.get("First_Name"));

            action.selectItemInCombobox(list_Leadsource, testData.get("Lead_Source"));
            action.click(btn_Campaign_Select);

            String winHandleBefore = driver.getWindowHandle();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            Thread.sleep(1000);
            action.clearInput(campaign_SearchName);
            action.enterInput(campaign_SearchName, testData.get("Campaign_SearchName"));
            action.click(campaign_SearchButton);
            action.click(campaign_SearchResult);
            Thread.sleep(1000);
            driver.switchTo().window(winHandleBefore);
            action.enterInput(account_name, testData.get("Account_Name"));
            action.enterInput(title, testData.get("Title"));
            action.enterInput(department, testData.get("Department"));
            action.click(assigned_select);

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Thread.sleep(1000);
            action.enterInput(assignedto_fname, testData.get("Assignedto_FirstName"));
            action.enterInput(assignedto_lname, testData.get("Assignedto_LastName"));
            action.enterInput(assignedto_uname, testData.get("Assignedto_UserName"));
            action.click(assignedto_cancel);

            Thread.sleep(1000);
            driver.switchTo().window(winHandleBefore);

            report(Status.PASS,"User fills the lead information",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }


    public void user_fills_the_address_information(HashMap<String,String> testData,ExtentTest extentTest) {
        try {
            action.enterInput(primary_address, testData.get("Primary_Address"));
            action.enterInput(state, testData.get("city"));
            action.enterInput(postalcode, testData.get("PostalCode"));
            action.enterInput(country, testData.get("Country"));
            action.click(copy_checkbox);
            report(Status.PASS,"User fills the address information",extentTest);
        } catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public void user_fills_the_email_details(HashMap<String,String> testData,ExtentTest extentTest) {
        try {
            action.enterInput(email_field1, testData.get("Email1"));
            action.click(add_address);
            action.enterInput(email_field2, testData.get("Email2"));
            action.click(email_delete);
            report(Status.PASS,"User fills the email details",extentTest);
        } catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public void user_clicks_on_save_button(ExtentTest extentTest) {
        try{
            action.click(Save);
            report(Status.PASS,"User clicks on save button",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public boolean user_verifies_the_creation_of_lead(ExtentTest extentTest) {
        boolean status=false;
        try {
            status=verification.isDisplayed(lead_verification);
            if(status) {
                report(Status.PASS,"User created the Lead",extentTest);
            } else{
                report(Status.FAIL,"User does not created the lead",extentTest);
            }
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
        return status;
    }

    public void user_logsout_from_application(ExtentTest extentTest) {
        try {
            action.click(logout);
            report(Status.PASS,"user logout from application",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public void user_edit_lead_information(HashMap<String,String> testData,ExtentTest extentTest) {
        try {

            action.enterInput(edit_fname, testData.get("First_Name"));
            action.enterInput(edit_lname, testData.get("Last_Name"));
            action.click(edit_Search);
            Thread.sleep(500);
            WebElement element = driver.findElement(By.xpath("//form[@id='MassUpdate']/table//tr[3]/td[2]/a[text()='Ms. Steffani Doss'][1]"));
            action.click(element);
            Thread.sleep(500);

            action.click(btnEdit);
            Thread.sleep(500);

            user_fills_the_address_information(testData,extentTest);
            user_clicks_on_save_button(extentTest);
            report(Status.PASS,"User edit Lead Information",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }

    public void user_enter_lead_fname_lname(String fname,String lname,ExtentTest extentTest){
        try {
            action.enterInput(edit_fname, fname);
            action.enterInput(edit_lname, lname);
            action.click(edit_Search);
            Thread.sleep(500);
            report(Status.PASS,"User enter lead - Firstname and Lastname",extentTest);
        }catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }
    public void user_delete_lead_information(ExtentTest extentTest) {
        try {
            WebElement element = driver.findElement(By.xpath("//form[@id='MassUpdate']/table//tr[3]/td[2]/a[text()='Ms. Steffani Doss'][1]"));
            action.click(element);
            Thread.sleep(500);

            action.click(delete);
            Thread.sleep(500);

            Alert alert = driver.switchTo().alert();
            alert.accept();
            report(Status.PASS,"User delete Lead Information",extentTest);
        } catch (Exception e){
            report(Status.FAIL,e.getMessage(),extentTest);
        }
    }


}
