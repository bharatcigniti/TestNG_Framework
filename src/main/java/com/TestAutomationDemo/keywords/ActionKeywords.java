package com.TestAutomationDemo.keywords;

import com.TestAutomationDemo.base.ConfigTestData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ActionKeywords {

    static WebDriver driver;
    static ConfigTestData configTestData;

    public ActionKeywords(ConfigTestData configTestData){
        this.configTestData=configTestData;
        this.driver = configTestData.driver;
    }

    public void openBrowser(){

    }

    public void closeBrowser(){
        try {
            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void navigate(String action){
        try {
            switch (action.toLowerCase()){
                case "forward":
                    driver.navigate().forward();
                    break;
                case "back":
                    driver.navigate().back();
                    break;
                case "refresh":
                    driver.navigate().refresh();
                    break;
                default:
                    driver.navigate().to(action);
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openNewTab(){
        Actions newTab = new Actions(driver);
        newTab.sendKeys(Keys.CONTROL + "t").perform();
    }

    public void closeTab(){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.close()");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void switchTo(String obj,String name){
        switch (obj.toLowerCase()){
            case "window":
                driver.switchTo().window(name);
                break;
            case "alert":
                driver.switchTo().alert();
                break;
            case "frame":
                driver.switchTo().frame(name);
                break;
            default:
                System.out.println("Give correct name");
                break;
        }
    }

    public void scroll(String action,Integer numberoftimes){
        try {
            switch (action.toLowerCase()){
                case "up":
                    for (int i = 0; i <= numberoftimes; i++) {
                        driver.findElement(By.tagName("body"))
                                .sendKeys(Keys.ARROW_UP);
                    }
                    break;
                case "down":
                    for (int i = 0; i <= numberoftimes; i++) {
                        driver.findElement(By.tagName("body"))
                                .sendKeys(Keys.ARROW_DOWN);
                    }
                    break;
                case "top":
                    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
                    break;
                case "bottom":
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean verifyPage(String url){
        boolean returnStatus = true;
        String getpageUrl=driver.getCurrentUrl();
        if(url.contains("https")){
            url = url.replaceAll("https://","");
        } else {
            url = url.replaceAll("http://","");
        }
        getpageUrl=getpageUrl.toLowerCase().trim();
        url=url.toLowerCase().trim();
        if(!getpageUrl.contains(url)){
            returnStatus = false;
        }
        return returnStatus;
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public boolean verifyText(WebElement element,String sExpectedText,String verifyTextOptions){
        boolean result=true;
        String sTemp = null;
        try{
            sTemp = element.getText();

            switch (verifyTextOptions) {
                case "EXACTMATCH":
                    result = sTemp.equals(sExpectedText);
                    break;
                case "EXACTMATCHIGNORECASE":
                    sTemp=sTemp.trim();
                    sExpectedText=sExpectedText.trim();
                    result=sTemp.equalsIgnoreCase(sExpectedText);
                    break;
                case "PARTIAL":
                    sTemp=sTemp.trim().toLowerCase();
                    sExpectedText=sExpectedText.trim().toLowerCase();
                    result = sTemp.contains(sExpectedText);
                    break;
            }

        }catch (Exception e) {
            result=false;
        }
        return result;
    }

    public String getValue(WebElement element){
        return element.getAttribute("value");
    }

    public void clearText(WebElement element){
        element.clear();
    }

    public boolean verifyValue(WebElement element,String sExpectedText,String verifyTextOptions ){
        boolean result=true;
        String sTemp = null;
        try{
            sTemp = element.getAttribute("value");

            switch (verifyTextOptions) {
                case "EXACTMATCH":
                    result = sTemp.equals(sExpectedText);
                    break;
                case "EXACTMATCHIGNORECASE":
                    sTemp=sTemp.trim();
                    sExpectedText=sExpectedText.trim();
                    result=sTemp.equalsIgnoreCase(sExpectedText);
                    break;
                case "PARTIAL":
                    sTemp=sTemp.trim().toLowerCase();
                    sExpectedText=sExpectedText.trim().toLowerCase();
                    result = sTemp.contains(sExpectedText);
                    break;
            }

        }catch (Exception e) {
            result=false;
        }
        return result;
    }

    public String getSelectedListItem(WebElement element){
        String itemvalue=null;
        try{
            Select selectElement = new Select(element);
            itemvalue= selectElement.getFirstSelectedOption().getText();
        } catch (Exception e){
            e.printStackTrace();
        }
        return itemvalue;
    }

    public List<WebElement> getAllListItems(WebElement element){
        List<WebElement> itemvalue=null;
        try{
            Select selectElement = new Select(element);
            itemvalue= selectElement.getOptions();
        } catch (Exception e){
            e.printStackTrace();
        }
        return itemvalue;
    }
    public void selectListItem(WebElement element, String itemToSelect){
        Select selectElement = new Select(element);
        try {
            selectElement.selectByVisibleText(itemToSelect);
        } catch (Throwable ignored) {

        }
    }

    public boolean verifyListItem(WebElement element,String itemtoVerify){
        boolean returnStatus=false;
        Select selectElement = new Select(element);
        try{
            List<WebElement> allOptions = selectElement.getOptions();
            for(int i=0; i<allOptions.size(); i++) {
                if(allOptions.get(i).equals(itemtoVerify)){
                    returnStatus=true;
                    break;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return returnStatus;
    }

    public void click(WebElement element){
        element.click();
    }

    public void action(WebElement element, String clickAction){
        Actions action = new Actions(driver);
        switch (clickAction.toLowerCase()){
            case "doubleclick":
                action.doubleClick(element).build().perform();
                break;
            case "rightclick":
                action.contextClick(element).perform();
                break;

            case "mouseover":
                action.moveToElement(element).perform();
                break;

        }
    }

    public void clickCheckBox(WebElement element, boolean chkStatus){
        try {
            if (element.isSelected() && chkStatus){

            } else if(element.isSelected()==false && chkStatus){
                // Check check box
                element.click();

            } else if(element.isSelected()==false && !chkStatus){

            } else if (element.isSelected() && !chkStatus) {
                // UnCheck check box
                element.click();

            }
         }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyIsObject(WebElement element, String action){
        boolean result =false;
        switch (action.toLowerCase()){
            case "isdisplayed":
                result= element.isDisplayed();
                break;
            case "isenabled":
                result= element.isEnabled();
                break;
            case "isSelected":
                result= element.isSelected();
                break;
        }
        return result;
    }
}
