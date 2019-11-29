package com.TestAutomationDemo.keywords;

import com.TestAutomationDemo.base.ConfigTestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.TestException;

public class Action {

    static WebDriver driver;
    static ConfigTestData configTestData;

    public Action(ConfigTestData configTestData){
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
    }

    //#######################################################
    //#############     BROWSER ACTIONS     #################
    //#######################################################
    public static String getBrowsername(){
        String browserName=null;
       try{
           browserName=configTestData.testBrowser;
       } catch (Exception e){
           e.printStackTrace();
       }
       return browserName;
    }

    public static void closeBrowser(){
        driver.close();
    }

    public static void quitBrowser(){
        driver.quit();
    }

    public static void openURL(String url){
        try{
            driver.get(url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method Description :: This method is Load a new pages page in the current browser window.
     * @param URL
     */
    public static void navigateToUrl(String URL){
        try{
            driver.navigate().to(URL);
        }catch (Exception e) {
         e.printStackTrace();
        }
    }
    /**
     * Method Description :: To move back a single "item" in the pages browser's history.
     */
    public static void navigateBack(){
        driver.navigate().back();
    }

    /**
     * Method Description :: To move a single "item" forward in the pages browser's history.
     */
    public static void navigateForward(){
        driver.navigate().forward();
    }

    /**
     * Method Description :: It refreshes the current pages page
     */
    public static void refreshPage(){
        driver.navigate().refresh();
    }

    /**
     * Method Description :: This method is used for get current Page title
     * @return
     */
    public static String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TestException("URL did not load");
        }
    }

    //###########################################################
    //#############     WEB ELEMENT ACTIONS     #################
    //###########################################################
    /**
     * Method Description :: To enter text into the Text Fields and Password Fields
     * @param webElement
     * @param str
     */
    public static void enterInput(WebElement webElement, String str){
        try{
            webElement.clear();
            webElement.sendKeys(str);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method Description :: This method is used to delete the text in an input box.
     * @param webElement
     */
    public static boolean clearInput(WebElement webElement){
        boolean result = true;
        try{
            webElement.clear();
            if(webElement.getAttribute("value").length()>0){
                result = false;
            }
        }catch (Exception e) {
            // Log.error("Exception in clear method :"+ e.getMessage());
        }
        return result;
    }

    /**
     * Method Description :: This method is used for select Item from combobox or List
     * @param wComboBoxName
     * @param sItemText
     * @return
     */
    public static void selectItemInCombobox(WebElement wComboBoxName,String sItemText){
        try{
            Select datatype = new Select(wComboBoxName);
            datatype.selectByVisibleText(sItemText);

        }catch (Exception e) {
          e.printStackTrace();
            // Log.error("Exception in generic_SelectItemInCombobox :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This Method will Click on provided WebElement
     * @param webElement : WebElement reference
     * @return
     */
    public static void click(WebElement webElement){
        try{
            webElement.click();
        }catch (Exception e) {
            // Log.error("Exception in click method :"+ e.getMessage());
        }
    }

    /**
     * Method Description :: This method is used for verify selected Item in combobox with given value
     * @param wComboBoxName
     * @param sExpectedItemText
     * @return
     */
    public static boolean verifyItemInCombobox(WebElement wComboBoxName,String sExpectedItemText){
        String actualText;
        boolean result = true;
        try {
            // Get the selected value from specified combo box
            Select selectedValue = new Select(wComboBoxName);
            actualText = selectedValue.getFirstSelectedOption().getText();

            // Verify that selected item in combo box is same as expected
            // item
            if (actualText.equalsIgnoreCase(sExpectedItemText)) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            //   Log.error("Exception in generic_VerifyItemInCombobox :"+ e.getMessage());
            result = false;
        }
        return result;
    }

}
