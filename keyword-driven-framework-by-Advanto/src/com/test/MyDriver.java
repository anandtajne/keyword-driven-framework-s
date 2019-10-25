package com.test;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.GlobalFunctions;
import com.util.ReadExcel;

@Test
public class MyDriver extends GlobalFunctions {

	ReadExcel readExcel;

	@BeforeClass
	public void beforeClass() {
		readExcel = new ReadExcel();
	}

	@Test(dataProvider = "getData")
	public void test(String sheetName, String columnName, String cellValue) throws IOException {
		if (cellValue.equalsIgnoreCase("n"))
			throw new SkipException("test mode is no");

		int rows = readExcel.rowCount(sheetName);
		for (int row = 1; row < rows; row++) {
			String keyword = readExcel.getCellValue(sheetName, "keyword", row);
			System.out.println(keyword);
			String locatorType = readExcel.getCellValue(sheetName, "locatorType", row);
			System.out.println(locatorType);
			String locatorValue = readExcel.getCellValue(sheetName, "locatorType", row);
			System.out.println(locatorValue);
			switch (keyword) {
			case ("openBrowser"):
				openBrowser();
				break;
			case ("enterText"):
//				enterText();
				break;
			default:
				break;
			}
		}
	}

	@DataProvider
	public String[][] getData() {
//		ReadExcel excel = new ReadExcel();
		return readExcel.getExcelData("Sheet1");
	}
}