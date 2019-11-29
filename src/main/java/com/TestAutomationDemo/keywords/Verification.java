package com.TestAutomationDemo.keywords;

import com.TestAutomationDemo.base.ConfigTestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Verification {

    static WebDriver driver;
    static ConfigTestData configTestData;

    public Verification(ConfigTestData configTestData){
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
    }




    /**
     * Method Description :: This method is used to verify if the web element is selected or not.
     * @param webElement
     * @return
     */
    public String isSelected(WebElement webElement){
        boolean elmSelected=false;
        try{
            return String.valueOf(webElement.isSelected());
        }catch (Exception e) {
            // Log.error("Exception in isSelected method :"+ e.getMessage());
        }
        return String.valueOf(elmSelected);
    }

    /**
     *  Method Description :: This method is used to verify if the web element is enabled or disabled within the webpage.
     * @param webElement
     * @return
     */
    public static String isEnabled(WebElement webElement){
        boolean elmEnabled=false;
        try{
            elmEnabled =webElement.isEnabled();
        }catch (Exception e) {
            elmEnabled=false;
            //  Log.error(webElement+"::: Exception in isEnabled method :"+ e.getMessage());
        }
        return String.valueOf(elmEnabled);
    }

    /**
     *  Method Description :: This method is used to verify a presence of a web element within the webpage
     * @param webElement
     * @return
     */
    public static boolean isDisplayed(WebElement webElement){
        boolean elmDisplayed=false;
        try{
            elmDisplayed= webElement.isDisplayed();
        }catch (Exception e) {
            elmDisplayed=false;
            //  Log.error("Exception in isDisplayed method :"+ e.getMessage());
        }
        return elmDisplayed;
    }
    /**
     * Method Description :: This method is get current page url and verifies with given url
     * @param Url
     * @return
     */
    public static boolean verifyPageUrl(String Url){
        boolean returnStatus = true;
        String getpageUrl=driver.getCurrentUrl();
        if(Url.contains("https")){
            Url = Url.replaceAll("https://","");
        } else {
            Url = Url.replaceAll("http://","");
        }
        getpageUrl=getpageUrl.toLowerCase().trim();
        Url=Url.toLowerCase().trim();
        if(!getpageUrl.contains(Url)){
            returnStatus = false;
        }
        return returnStatus;
    }
}
