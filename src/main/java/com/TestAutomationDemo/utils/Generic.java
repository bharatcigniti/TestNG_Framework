package com.TestAutomationDemo.utils;

import com.TestAutomationDemo.base.ConfigTestData;
import com.TestAutomationDemo.constants.GlobalConstants;
import org.openqa.selenium.Platform;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * @Author Bharath Kumar Reddy V
 * @Date 21-Nov-2019
 */
public class Generic {

    static ResourceBundle rbConfig =null;

    private static Platform platform = null;
    ConfigTestData configTestData=null;

    public Generic(ConfigTestData configTestData){
        this.configTestData=configTestData;
    }


    /*	To get the host OS name */
    public static Platform getCurretnPlatform(){
        if(platform == null){
            String osname = System.getProperty("os.name").toLowerCase();
            if(osname.contains("win")){
                platform = Platform.WINDOWS;
            } else if(osname.contains("nix") || osname.contains("nux") || osname.contains("aix")){
                platform= Platform.LINUX;
            } else if(osname.contains("mac")){
                platform= Platform.MAC;
            }
        }

        return platform;
    }

    /*	To get the ComputerName */
    public static String getComputerName() throws Exception{
        String hostname = "Unknown";
        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            ex.fillInStackTrace();
            System.out.println("Hostname can not be resolved");
        }
        return (hostname);
    }
    /**
     * Method Description :: Verify Text actual and expected
     * @param sActualText
     * @param sExpectedText
     * @param verifyTextOptions
     * @return
     */
    public static boolean verifyText(String sActualText, String sExpectedText, String verifyTextOptions){
        boolean result=true;
        try{
            switch (verifyTextOptions) {
                case "EXACTMATCH":
                    result = sActualText.equals(sExpectedText);
                    break;
                case "EXACTMATCHIGNORECASE":
                    sActualText=sActualText.trim();
                    sExpectedText=sExpectedText.trim();
                    result=sActualText.equalsIgnoreCase(sExpectedText);
                    break;
                case "PARTIAL":
                    sActualText=sActualText.trim().toLowerCase();
                    sExpectedText=sExpectedText.trim().toLowerCase();
                    result = sActualText.contains(sExpectedText);
                    break;
            }

        }catch (Exception e) {
            result=false;
            Log.error("Exception in verifyText :"+ e.getMessage());
        }
        return result;
    }


    public static String readFile(String filepath) throws IOException{
        File file = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while(line!=null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();

        } finally {
            bufferedReader.close();
        }
    }

    public String getSuiteXmlGroupName(String[] groupnames){
        String groupName=null;
        for(int i=0;i<=groupnames.length-1;i++){
            groupName = groupnames[0];
        }
        return groupName;

    }





    // Load the Config properties file from test source
    public static void loadConfigProp() throws Exception{
        File file = new File(GlobalConstants.CONFIG_PROP_FILE_PATH);
        URL[] urls = {file.toURI().toURL()};
        ClassLoader loader = new URLClassLoader(urls);
        rbConfig = ResourceBundle.getBundle("config", Locale.getDefault(), loader);
    }
    // Read the Config properties file from test source
    public static String readConfigProp(String key){

        try {
            loadConfigProp();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rbConfig.getString(key);
    }
    // Read the Config properties file from test source
    public static void readConfigProp(){
        ClassLoader loader=null;
        String path=null;
        try {
            File file = new File(GlobalConstants.CONFIG_PROP_FILE_PATH);
            URL[] urls = {file.toURI().toURL()};
            loader = new URLClassLoader(urls);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResourceBundle rbTestdata = ResourceBundle.getBundle("config", Locale.getDefault(), loader);
        GlobalConstants.LOCAL_GRID_HUB = rbTestdata.getString("localGridHub");
        GlobalConstants.SELENIUM_WEB_DRIVERS_PATH = rbTestdata.getString("seleniumGridPath");
        GlobalConstants.TEST_DATA_PATH = GlobalConstants.workDir + rbTestdata.getString("testdata.path");
        GlobalConstants.TEST_DATA_PATH = GlobalConstants.workDir + rbTestdata.getString("testdata.path")+File.separator+rbTestdata.getString("testdata.name");


        GlobalConstants.MULTI_BROWSER = rbTestdata.getString("multiBrowser");

        if(!getCurretnPlatform().is(Platform.WINDOWS)){
            GlobalConstants.SELENIUM_WEB_DRIVERS_PATH=GlobalConstants.SELENIUM_WEB_DRIVERS_PATH.replaceAll("\\\\", File.separator);
        }

    }

    public HashMap<String,String> getTestData(String xlFilePath, String sheetName,String testCaseName)  {
        int rownum=0;
        HashMap<String,String> excelData=new HashMap<String,String>();
        ExcelUtils excelUtils = new ExcelUtils();
        System.out.println("xlFilePath:"+xlFilePath);
        System.out.println("sheetName:"+sheetName);
        System.out.println("testCaseName:"+testCaseName);
        try {
            excelUtils.getExcelFile(xlFilePath,sheetName);

            int colCount = excelUtils.xlsxColumnCount();

            int rowCount= excelUtils.xlsxRowCount();

            for(int i=1;i<rowCount;i++){
                if(testCaseName.equalsIgnoreCase(excelUtils.getCellData_XLSX(i,1))){
                    rownum=i;
                    break;
                }
            }

            for (int j = 1; j < colCount; j++) {
                excelData.put(excelUtils.getCellData_XLSX(0,j),excelUtils.getCellData_XLSX(rownum,j));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return excelData;
    }

}
