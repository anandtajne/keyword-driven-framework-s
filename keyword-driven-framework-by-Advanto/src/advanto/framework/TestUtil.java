package advanto.framework;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestUtil {
	public FileInputStream fis;
	public Workbook wb;
	public Sheet sh;

	public boolean verifyTestCaseMode(String TCName, ReadExcel re) {
		int row = re.getRowCount("TestCases");
		for (int i = 1; i < row; i++) {
			String tc = re.getCellData("TestCases", "ID", i);
			// System.out.println(tc);
			if (tc.equalsIgnoreCase(TCName))

			{
				String m = re.getCellData("TestCases", "Mode", i);
				// System.out.println(m);
				if (m.equalsIgnoreCase("Y")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String args[]) throws BiffException, IOException {
		ReadExcel re = new ReadExcel();
		TestUtil tu = new TestUtil();
		boolean b = tu.verifyTestCaseMode("TC_004", re);
		System.out.println(b);
	}

}
