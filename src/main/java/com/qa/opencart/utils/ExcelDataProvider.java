package com.qa.opencart.utils;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	
	public static final String TEST_DATA_SHEET_PATH = "C:\\Users\\rahul.n\\eclipse-workspace\\Selenium_Saviant_Framework\\src\\test\\resources\\TestData\\OpenCartTestData.xlsx";

	public static Object[][] getTestData(String sheetName) throws Exception {
		System.out.println("Reading the Data from Sheet:" + sheetName);

		Object data[][] = null;

		FileInputStream file = new FileInputStream(TEST_DATA_SHEET_PATH);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(sheetName);
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		
		return data;

	}


}
