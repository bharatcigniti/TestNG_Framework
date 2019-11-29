package com.sugarCRM.generic;

import com.TestAutomationDemo.utils.ExcelUtils;

import java.util.HashMap;

public class ProjectGeneric {

    public HashMap<String,String> getTestData(String xlFilePath, String sheetName, String testCaseName)  {
        int rownum=0;
        HashMap<String,String> excelData=new HashMap<String,String>();
        ExcelUtils excelUtils = new ExcelUtils();

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
