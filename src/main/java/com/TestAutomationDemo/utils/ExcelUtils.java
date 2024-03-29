package com.TestAutomationDemo.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @Author Bharath
 * @Date 19-March-2018
 * @Updated 23-Oct-2018
 */
public class ExcelUtils {
	private XSSFSheet xlsxWorkSheet;
	private XSSFWorkbook xlsxWorkBook;
	private XSSFCell xlsxCell;
	@SuppressWarnings("unused")
	private XSSFRow xlsxRow;
	public int obtainedRow=0;

	private HSSFSheet xlsWorkSheet;
	private HSSFWorkbook xlsWorkBook;
	private HSSFCell xlsCell;
	@SuppressWarnings("unused")
	private HSSFRow xlsRow;

	public String cellTextVal = null;
	public String cellIntegerVal = null;
	public int colIndex = 0;
	public String excel_Path;
	public FileInputStream strInputdataExtractorFile = null;
	public XSSFWorkbook objdataExtractorWKB = null;
	public XSSFSheet objdataExtractorSheet = null;
	public Row objdataExtractorRow = null;
	private String sheetName = null;

	/** To get the Excel-XLSX File with Path and SheetName */
	public void getExcelFile(String Path,String SheetName){
		try
		{
			File file = new File(Path);
			if(file.getAbsolutePath().endsWith(".xlsx")){
				FileInputStream fis = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(fis);
				xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);
			} else if(file.getAbsolutePath().endsWith(".xls")){
				FileInputStream fis = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(fis);
				xlsWorkSheet = xlsWorkBook.getSheet(SheetName);
			}

			excel_Path = Path;
			sheetName= SheetName;

		}catch (Exception e){
			e.printStackTrace();
		}
	}


	/** To Return the Excel-XLSX Values given Path to the File and Sheet Name */
	public String[][] getTableArray(String FilePath, String SheetName) throws Exception{
		String[][] tabArray = null;
		try
		{
			File file = new File(FilePath);
			if(file.getAbsolutePath().endsWith(".xlsx"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(ExcelFile);
				xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = xlsxRowCount();
				int totalCols = xlsxColumnCount();
				tabArray=new String[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLSX(i,j);
						cj++;
					}
					ci++;
				}
			}
			else if(file.getAbsolutePath().endsWith(".xls"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(ExcelFile);
				xlsWorkSheet = xlsWorkBook.getSheet(SheetName);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = xlsRowCount();
				int totalCols = xlsColumnCount();
				tabArray=new String[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLS(i,j);
						cj++;
					}
					ci++;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			throw new Exception("Could not Find the Excel File/Sheet");
		}
		catch (Exception e)
		{
			throw new Exception("Could not Open the Excel File");
		}
		return(tabArray);
	}


	/** To Return the Excel-XLSX Values given Path to the File */
	public String[][] getTableArray(String FilePath) throws Exception
	{
		String[][] tabArray = null;
		try
		{
			File file = new File(FilePath);
			if(file.getAbsolutePath().endsWith(".xlsx"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(ExcelFile);
				xlsxWorkSheet = xlsxWorkBook.getSheetAt(0);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = xlsxRowCount();
				int totalCols = xlsxColumnCount();
				tabArray=new String[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLSX(i,j);
						cj++;
					}
					ci++;
				}
			}
			else if(file.getAbsolutePath().endsWith(".xls"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(ExcelFile);
				xlsWorkSheet = xlsWorkBook.getSheetAt(0);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = xlsRowCount();
				int totalCols = xlsColumnCount();
				tabArray=new String[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLS(i,j);
						cj++;
					}
					ci++;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			throw new Exception("Could not Find the Excel File/Sheet");
		}
		catch (Exception e)
		{
			throw new Exception("Could not Open the Excel File");
		}
		return(tabArray);
	}




	/** To get cell data from Excel-XLSX */
	public String getCellData_XLSX(int RowNum, int ColNum) throws Exception
	{
		String CellData = null;
		try
		{
			xlsxCell = xlsxWorkSheet.getRow(RowNum).getCell(ColNum);
			if(xlsxCell.getCellType() == Cell.CELL_TYPE_STRING )
			{
				String stringCellData = xlsxCell.getStringCellValue();
				CellData = stringCellData;
			}
			if(xlsxCell.getCellType() == Cell.CELL_TYPE_FORMULA )
			{
				String stringCellData = xlsxCell.getRawValue();
				CellData = stringCellData;
			}

			if(xlsxCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			{
				double numericCellData =  xlsxCell.getNumericCellValue();
				CellData = String.valueOf(numericCellData);

			}
			else if(xlsxCell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
			{
				boolean booleanCellData =  xlsxCell.getBooleanCellValue();
				CellData = String.valueOf(booleanCellData);
			}
			return CellData;
		}
		catch (Exception e)
		{
			return"";
		}
	}

	/** To get cell data from Excel-XLS */
	public String getCellData_XLS(int RowNum, int ColNum) throws Exception
	{
		String CellData = null;
		try
		{
			xlsCell = xlsWorkSheet.getRow(RowNum).getCell(ColNum);
			if(xlsCell.getCellType() == Cell.CELL_TYPE_STRING )
			{
				String stringCellData = xlsCell.getStringCellValue();
				CellData = stringCellData;
			}
			/*		else if(xlsCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			{
				double numericCellData =  xlsCell.getNumericCellValue();
				CellData = numericCellData;
			}
			else if(xlsCell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
			{
				boolean booleanCellData =  xlsCell.getBooleanCellValue();
				CellData = booleanCellData;
			}	*/
			return CellData;
		}
		catch (Exception e)
		{
			return"";
		}
	}

	/** To get Excel-XLSX Row Count */
	public int xlsxRowCount()
	{
		int rowNum = xlsxWorkSheet.getLastRowNum()+1;
		return rowNum;
	}
	public int getrowIndex(String rowVal,String colName) throws Exception{

		int rows = xlsxRowCount();

		String cellTextVal;
		//while (rows.hasNext()) {
		for (int i=1;i<=rows;i++){

			cellTextVal = getCellData_XLSX(i,colName);

			if (cellTextVal.equals(rowVal)) {
				obtainedRow = i;
				break;
			}

		}
		return obtainedRow;
	}

	/** To get Excel-XLS Row Count */
	public int xlsRowCount()
	{
		int rowNum = xlsWorkSheet.getLastRowNum()+1;
		return rowNum;
	}

	/** To get Excel-XLSX Column Count */
	public int xlsxColumnCount()
	{
		int rowNum = xlsxWorkSheet.getRow(0).getLastCellNum();
		return rowNum;
	}

	/** To get cell data from Excel-XLS */
	public String getCellData_XLSX(int RowNum, String ColName) throws Exception{
		return getCellData_XLSX(RowNum,getcolIndex(ColName));
	}

	/** To get cell data from Excel-XLS */
	public String getCellData_XLSX(String rowName, String ColName) throws Exception{
		return getCellData_XLSX(getrowIndex(rowName,ColName),getcolIndex(ColName));
	}


	/** To get Excel-XLS Column Count */
	public int xlsColumnCount()
	{
		int rowNum = xlsWorkSheet.getRow(0).getLastCellNum();
		return rowNum;
	}
	/** To get Excel-XLXS Column Index */
	public int getcolIndex(String colName)throws Exception{
		ArrayList<Row> testscriptColumnList = extractTestScriptColumns();
		Iterator coulmns = testscriptColumnList.iterator();
		int breakVal=0;
		while (coulmns.hasNext()) {
			XSSFRow currentRow = (XSSFRow) coulmns.next();
			Iterator cells = currentRow.cellIterator();
			while (cells.hasNext()) {
				// rowIncrementer=0;
				breakVal++;
				XSSFCell cell = (XSSFCell) cells.next();
				cellTextVal = cell.getRichStringCellValue().toString();
				// objectArrayList.add(cellTextVal);

				if(cellTextVal.equals(colName)) {
					colIndex = breakVal;
					break;
				}
			}
			if(colIndex>0)
				break;
		}
		colIndex = colIndex - 1;
		return colIndex;
	}

	public ArrayList<Row> extractTestScriptColumns() throws Exception {
		//Path to Excel filename
		File strExecutiondataExtractorFile = new File(excel_Path);

		strInputdataExtractorFile = new FileInputStream(strExecutiondataExtractorFile);
		objdataExtractorWKB = new XSSFWorkbook(strInputdataExtractorFile);
		objdataExtractorSheet = objdataExtractorWKB.getSheet(sheetName);

		ArrayList<Row> targetTestScriptColumns = new ArrayList<Row>();
		objdataExtractorRow = objdataExtractorSheet.getRow(0);
		targetTestScriptColumns.add(objdataExtractorRow);
		return targetTestScriptColumns;
	}

	public HashMap<String,String> readExcel(String xlFilePath, String sheetName, String testCaseName)  {
		int rownum=0;
		HashMap<String,String> excelData=new HashMap<String,String>();

		try {
			getExcelFile(xlFilePath,sheetName);

			int colCount = xlsxColumnCount();

			int rowCount= xlsxRowCount();

			for(int i=1;i<rowCount;i++){
				if(testCaseName.equalsIgnoreCase(getCellData_XLSX(i,1))){
					rownum=i;
					break;
				}
			}

			for (int j = 1; j < colCount; j++) {
				excelData.put(getCellData_XLSX(0,j),getCellData_XLSX(rownum,j));
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return excelData;
	}
}