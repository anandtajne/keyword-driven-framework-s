package advanto.framework;

import java.io.IOException;

import org.testng.SkipException;
//import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class MyDriver extends GlobalFunctions {
	// public ReadExcel re;
	@DataProvider
	public String[][] getData() throws BiffException, IOException {
		ReadExcel re = new ReadExcel();
		return re.getExcel("Testcases");
	}

	@Test(dataProvider = "getData")
	public void testcases(String s1, String s2, String s3) throws BiffException, IOException {
		if (s3.equalsIgnoreCase("N"))
			throw new SkipException("Test Mode is No");

		ReadExcel re = new ReadExcel();
		int rows = re.getRowCount(s1);
		for (int i = 1; i < rows; i++) {
			String keyword = re.getCellData(s1, "Keyword", i);
			System.out.println("keyword : " + keyword);
			String locType = re.getCellData(s1, "LocatorType", i);
			System.out.println("locType : " + locType);
			String locValue = re.getCellData(s1, "LocatorValue", i);
			System.out.println("locValue : " + locValue);
			String data = re.getCellData(s1, "Data", i);
			System.out.println("data : " + data);

			switch (keyword) {
			case ("openBrowser"):
				openBrowser(data);
				break;
			case ("enterText"):
				enterText(locType, locValue, data);
				break;
			case ("click"):
				click(locType, locValue);
				break;
			case ("verifyText"):
				verifyText(locType, locValue, data);
				break;
			case ("dropDownSelect"):
				dropDownSelect(locType, locValue, data);
				break;
			case ("multiDropDownSelect"):
				multiDropDownSelect(locType, locValue, data);
				break;
			case ("alertBox"):
				alertBox(locType, locValue);
				break;
			case ("alertBoxCancel"):
				alertBoxCancel(locType, locValue);
				break;
			case ("alertPromptBox"):
				alertPromptBox(locType, locValue, data);
				break;
			case ("closeBrowser"):
				closeBrowser();
				break;
			default:
				System.out.println("value not found");
			}
		}

	}

}
