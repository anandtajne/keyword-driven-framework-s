package com.mkyong;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelRead {

	private static final String EXCEL_FILE_LOCATION = System.getProperty("user.dir") + "\\src\\com\\data\\testcase.xls";

	public static void main(String[] args) {

		Workbook workbook = null;

		try {
			workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));

			Sheet sheet = workbook.getSheet(0);

			Cell ID = sheet.getCell(0, 0);
			System.out.print(ID.getContents() + ":"); // Column ID

			Cell testCase = sheet.getCell(0, 1);
			System.out.println(testCase.getContents()); // tc_001

			Cell summary = sheet.getCell(1, 0);
			System.out.print(summary.getContents() + ":"); // Column Summary

			Cell summary_value = sheet.getCell(1, 1);
			System.out.println(summary_value.getContents()); // 1st test case

			Cell mode = sheet.getCell(2, 0);
			System.out.print(mode.getContents() + ":");

			Cell mode_value = sheet.getCell(2, 1);
			System.out.println(mode_value.getContents());

			System.out.print(ID.getContents() + ":"); // Test Count + :
			testCase = sheet.getCell(0, 2);
			System.out.println(testCase.getContents()); // 2

			System.out.print(summary.getContents() + ":"); // Result + :
			summary_value = sheet.getCell(1, 2);
			System.out.println(summary_value.getContents()); // Passed 2

			System.out.print(mode.getContents() + ":"); // Test Count + :
			testCase = sheet.getCell(2, 2);
			System.out.println(mode_value.getContents()); // 2

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}