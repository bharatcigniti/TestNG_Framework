package com.TestAutomationDemo.constants;


import java.io.File;

/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */
public class GlobalConstants {

    public static String workDir = System.getProperty("user.dir");
    public static String CONFIG_PROP_FILE_PATH = workDir+ File.separator+"src"+File.separator+"test"+File.separator+"resources";


    public static String C_CHECK = "CHECK";
    public static String C_UNCHECK = "UNCHECK";
    public static String C_ALERT_ACCEPT = "ACCEPT";
    public static String C_ALERT_DECLINE = "DECLINE";

    public static String VERIFY_TEXT_EXACTMATCH = "EXACTMATCH";
    public static String VERIFY_TEXT_EXACTMATCHIGNORECASE = "EXACTMATCHIGNORECASE";
    public static String VERIFY_TEXT_PARTIAL = "PARTIAL";

    public static final int SHORT_TIMEOUT = 5;
    public static final int MEDIUM_TIMEOUT = 10;
    public static final int LONG_TIMEOUT = 20;
    public static final int PAGE_TIMEOUT = 30;


    public static String GROUP_NAME=null;

    public static String LOCAL_GRID_HUB=null;
    public static String SELENIUM_WEB_DRIVERS_PATH=null;

    public static String TEST_DATA_PATH=null;

    public static String MULTI_BROWSER=null;

    public static int MAX_RETRY_COUNT = 0;
}
