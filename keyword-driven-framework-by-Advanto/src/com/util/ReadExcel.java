package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	Workbook workbook;
	Sheet sheet;
	Properties properties;

	public ReadExcel() {

		properties = new Properties();

		FileInputStream propertiesFile;
		try {
			propertiesFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\com\\data\\config.properties");

			properties.load(propertiesFile);

			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + properties.getProperty("excel_file_path"));

			workbook = Workbook.getWorkbook(fileInputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int rowCount(String sheetName) {
		return workbook.getSheet(sheetName).getRows();
	}

	public int columnCount(String sheetName) {
		return workbook.getSheet(sheetName).getColumns();
	}

	public String getCellValue(String sheetName, int row, int column) {
		return workbook.getSheet(sheetName).getCell(column, row).getContents();
	}

//	public String getCellValue(String sheetName, int row, String columnName) {
//		sheet = workbook.getSheet(sheetName);
//		int totalNoColumns = sheet.getColumns();
//		for (int col = 0; col < totalNoColumns; col++) {
//			String column = sheet.getCell(col, row).getContents();
//			if (columnName.equalsIgnoreCase(column)) {
//				return column;
//			}
//		}
//		return "column value not found...!!";
//	}

	public String getCellValue(String SheetName, String ColName, int rowNum) {
		sheet = workbook.getSheet(SheetName);
		int totalNoColumns = sheet.getColumns();
		for (int i = 0; i < totalNoColumns; i++) {
			String column = sheet.getCell(i, 0).getContents();
			if (column.equalsIgnoreCase(ColName)) {
				return sheet.getCell(i, rowNum).getContents();
			}
		}
		return "column value not found...!!";
	}

	public String[][] getExcelData(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		int totalNoRows = sheet.getRows();
		int totalNoColumns = sheet.getColumns();
		String[][] sheetData = new String[totalNoRows - 1][totalNoColumns];
		for (int row = 0; row < totalNoRows - 1; row++) {
			for (int col = 0; col < totalNoColumns; col++) {
//				System.out.print(sheet.getCell(col, row).getContents() + "\t");
				sheetData[row][col] = sheet.getCell(col, row + 1).getContents();
			}
//			System.out.println("\t");
		}
		return sheetData;
	}

	public static void main(String[] args) {
		ReadExcel readExcel = new ReadExcel();
//		System.out.println("total rows present in sheet : " + readExcel.rowCount("Sheet1"));
//		System.out.println("total column present in sheet : " + readExcel.columnCount("Sheet1"));
//		System.out.println("cell value : " + readExcel.getCellValue("Sheet1", 0, 1));
		System.out.println("cell value : " + readExcel.getCellValue("Sheet1", "mode", 0));
		String[][] excel = readExcel.getExcelData("Sheet1");
		int rows = excel.length;
		int columns = excel[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(excel[i][j] + " \t ");
			}
			System.out.println();
		}

	}

}