package com.sugarCRM.pages;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.keywords.Action;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 22-Nov-2019
 */
public class LoginPage {

    WebDriver driver;
    ConfigTestData configTestData;
    public LoginPage(ConfigTestData configTestData) {
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
        PageFactory.initElements(this.driver,this);
    }

    //variable Declaration
    public String login_URL = "http://10.62.65.229:81/sugarcrm/index.php?action=Login&module=Users";

    //********* Sign In Page Web Elements by using Page Factory*********

    @CacheLookup
    @FindBy(id = "user_name")
    public WebElement input_UserName;

    @CacheLookup
    @FindBy(id = "user_password")
    public WebElement input_Password;

    @CacheLookup
    @FindBy(id = "login_button")
    public WebElement btn_Login;

    public void navigate_SugarCRMLoginPage(){
        driver.navigate().to(login_URL);
    }

    public boolean verify_SugarCRMLoginPage_isDisplayed(){

        boolean status=true;
        if(!driver.getCurrentUrl().contains(login_URL)){
            status =  false;
        }
        return status;
    }

    public void user_Login_To_SugarCRM(HashMap<String,String> testData){
        try {
            Action.enterInput(input_UserName,testData.get("UserName"));
            Action.enterInput(input_Password,testData.get("Password"));
            Thread.sleep(15);
            Action.click(btn_Login);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
