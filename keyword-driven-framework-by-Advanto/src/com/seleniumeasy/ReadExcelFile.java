package com.seleniumeasy;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcelFile {

	public void readExcel() throws BiffException, IOException {
		String FilePath = System.getProperty("user.dir") + "\\src\\com\\data\\testcase.xls";
		FileInputStream inputStream = new FileInputStream(FilePath);
		Workbook workbook = Workbook.getWorkbook(inputStream);
 
		// TO get the access to the sheet
		Sheet sheet = workbook.getSheet("Sheet1");

		// To get the number of rows present in sheet
		int totalNoOfRows = sheet.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sheet.getColumns();

		for (int row = 0; row < totalNoOfRows; row++) {

			for (int col = 0; col < totalNoOfCols; col++) {

				System.out.print(sheet.getCell(col, row).getContents() + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws BiffException, IOException {
		ReadExcelFile excelFile = new ReadExcelFile();
		excelFile.readExcel();
	}
}