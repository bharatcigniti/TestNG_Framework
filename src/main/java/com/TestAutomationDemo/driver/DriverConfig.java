package com.TestAutomationDemo.driver;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.constants.GlobalConstants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */

public class DriverConfig {

    private RemoteWebDriver driver;
    private ConfigTestData configTestData=null;
    public DriverConfig(ConfigTestData configTestData) {
        this.configTestData = configTestData;
    }
    String[] browsers=null;
    int n=0;
    public String getMultiBrowser(){
        Random Dice = new Random();
        browsers = GlobalConstants.MULTI_BROWSER.split(",");
        n = Dice.nextInt(browsers.length);
        System.out.println("rando:"+n);
        return browsers[n];
    }
    public RemoteWebDriver getDriver() {
        try {
            if(configTestData.testBrowser.equalsIgnoreCase("multiBrowser")) {
                configTestData.testBrowser = getMultiBrowser();
            }

            driver= setWebDriverManage(new RemoteWebDriver(new URL(GlobalConstants.LOCAL_GRID_HUB), getBrowserCapabilities()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }
    protected RemoteWebDriver setWebDriverManage(RemoteWebDriver driver) {
        driver.manage().timeouts().implicitlyWait(GlobalConstants.PAGE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    private DesiredCapabilities getBrowserCapabilities()  {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(
                CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        switch (configTestData.testBrowser.toLowerCase()) {
            case "firefox":
            case "ff":
//                System.setProperty(
//                        "webdriver.gecko.driver", GlobalConstants.SELENIUM_WEB_DRIVERS_PATH + "geckodriver.exe");
//                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setBrowserName("firefox");
                break;
            case "ie":
            case "internet explorer":
            case "ie11":
//                System.setProperty(
//                        "webdriver.ie.driver", GlobalConstants.SELENIUM_WEB_DRIVERS_PATH + "IEDriverServer.exe");
                capabilities = DesiredCapabilities.internetExplorer();
                // capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                // capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                capabilities.setBrowserName("internet explorer");
                capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

                break;
            case "chrome":
                ChromeOptions chromeoptions = new ChromeOptions();
                chromeoptions.addArguments("test-type");
                chromeoptions.addArguments("disable-popup-blocking");
                capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
//                System.setProperty(
//                        "webdriver.chrome.driver", GlobalConstants.SELENIUM_WEB_DRIVERS_PATH + "chromedriver.exe");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
                capabilities.setBrowserName("chrome");

                break;
            case "safari":
                SafariOptions safarioptions = new SafariOptions();
                //    options.setUseCleanSession(true);
                capabilities = DesiredCapabilities.safari();
                capabilities.setCapability(SafariOptions.CAPABILITY, safarioptions);
                capabilities.setBrowserName("safari");

                break;
            case "edge":
            case "microsoft edge":

                // driver = new EdgeDriver();
//                System.setProperty(
//                        "webdriver.edge.driver",
//                        GlobalConstants.SELENIUM_WEB_DRIVERS_PATH + "MicrosoftWebDriver.exe");
                capabilities = DesiredCapabilities.edge();
                //    options.setUseCleanSession(true);
                capabilities.setBrowserName(DesiredCapabilities.edge().getBrowserName());
                // DesiredCapabilities capabilities = DesiredCapabilities.edge(); Tried as well
                capabilities.setCapability("acceptSslCerts", "true");
                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy("eager");

                break;
            default:
                throw new NotFoundException(
                        "Browser Not Found. Please Provide a Valid Browser ex: firefox,safari,ie,chrome");
        }
        return capabilities;
    }
}
