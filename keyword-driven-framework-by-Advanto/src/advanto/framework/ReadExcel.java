package advanto.framework;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
//	public FileInputStream fileInputStream;
	public Workbook workbook;
	public Sheet sheet;

	public ReadExcel() throws IOException, BiffException {
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\advanto\\framework\\readexceldata.xls");
		workbook = Workbook.getWorkbook(fileInputStream);
	}

	public int getRowCount(String sheetname) {
		sheet = workbook.getSheet(sheetname);
		return sheet.getRows();
	}

	public int getColumnCount(String Sheetname) {
		sheet = workbook.getSheet(Sheetname);
		return sheet.getColumns();
	}

	public String getCellData(String sheetname, int colNum, int rowNum) {
		sheet = workbook.getSheet(sheetname);
		return sheet.getCell(colNum, rowNum).getContents();
	}

	public String getCellData(String SheetName, String ColName, int rowNum) {
		sheet = workbook.getSheet(SheetName);
		int cols = getColumnCount(SheetName);

		for (int i = 0; i < cols; i++) {
			String column = sheet.getCell(i, 0).getContents();
			if (column.equalsIgnoreCase(ColName)) {
				return sheet.getCell(i, rowNum).getContents();
			}
		}
		return "value not found..!!";
	}

	public String[][] getExcel(String sheetname) {
		sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows();
		System.out.println("ROWS: " + rows);
		int cols = sheet.getColumns();
		String data[][] = new String[rows - 1][cols];
		for (int i = 0; i < rows - 1; i++) {
			System.out.println("Row:" + i);
			for (int j = 0; j < cols; j++) {
				System.out.print("col:" + j + " ");
				data[i][j] = sheet.getCell(j, i + 1).getContents();
				System.out.println(data[i][j]);
			}
			System.out.println();
		}
		return data;
	}

	public static void main(String args[]) throws BiffException, IOException {
		ReadExcel readExcel = new ReadExcel();
		int row = readExcel.getRowCount("Testcases");
		System.out.println("total row count : " + row);

		int column = readExcel.getColumnCount("Testcases");
		System.out.println("total column count : " + column);

		String cellValue = readExcel.getCellData("Testcases", 2, 0);
		System.out.println("cell value : " + cellValue);

		String cellValue2 = readExcel.getCellData("Testcases", "mode", 0);
		System.out.println("cell value by column name : " + cellValue2);

		String[][] mydata = readExcel.getExcel("Testcases");
		int rows = mydata.length;
		int cols = mydata[0].length;
		for (int i = 0; i < rows; i++) {
			System.out.println("Row:" + i);
			for (int j = 0; j < cols; j++) {
				System.out.print("col:" + j + " ");
				System.out.print(mydata[i][j] + "\t");
			}
			System.out.println();

		}

//		readExcel.getExcel("Testcases");
	}
}
