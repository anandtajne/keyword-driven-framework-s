package advanto.framework;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class GlobalFunctions {
	WebDriver driver;
	ReadProperties readProperties;
	FileInputStream fis;

	public void openBrowser(String data) throws IOException {
		// FileInputStream fis= new FileInputStream("E:\\Selenium\\Webdriver\\Advanto
		// repeat\\Advanto_Project\\lib\\chromedriver.exe");
		readProperties = new ReadProperties();
		// p.load(fis);
		String browser = readProperties.getData("browser");
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + readProperties.getData("browser_file_path"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
		/*
		 * else if (browser.equalsIgnoreCase("Firefox")) { System.
		 * setProperty("E:\\Selenium\\Webdriver\\Advanto repeat\\MyFramework\\lib\\geckodriver-v0.23.0-arm7hf.exe"
		 * , browser); obj=new ChromeDriver();
		 * 
		 * 
		 * }
		 */
		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "D:\\tools\\testing\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}
		driver.get(data);
	}

	public By getLocators(String locType, String locValue) {
		switch (locType) {
		case "id":
			return By.id(locValue);
		case "name":
			return By.name(locValue);
		case "xpath":
			return By.xpath(locValue);
		case "css":
			return By.cssSelector(locValue);
		default:
			return null;
		}

	}

	public void enterText(String locType, String locValue, String data) {
		By locator = getLocators(locType, locValue);
		try {
			driver.findElement(locator).sendKeys(data);
		} catch (Exception e) {
			System.out.println("fail");
		}
	}

	public void dropDownSelect(String locType, String locValue, String data) {
		By locator = getLocators(locType, locValue);
		try {
			Select select = new Select(driver.findElement(locator));
			select.selectByVisibleText(data);
		} catch (Exception e) {
			System.out.println("fail");
		}
	}

	public void multiDropDownSelect(String locType, String locValue, String data) {
		By locator = getLocators(locType, locValue);
		try {
			String[] value = data.split(", ");
			Select select = new Select(driver.findElement(locator));
			for (int i = 0; i < value.length; i++) {
				select.selectByVisibleText(value[i]);
			}
		} catch (Exception e) {
			System.out.println("fail");
		}
	}

	public void alertBox(String locType, String locValue) {
		By locator = getLocators(locType, locValue);
		try {
			Thread.sleep(2000);
			click(locType, locValue);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.accept();
		} catch (Exception e) {
			System.out.println("fail");
		}
	}

	public void alertBoxCancel(String locType, String locValue) {
		By locator = getLocators(locType, locValue);
		try {
			Thread.sleep(2000);
			click(locType, locValue);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			alert.dismiss();
		} catch (Exception e) {
			System.out.println("fail");
		}
	}

	public void alertPromptBox(String locType, String locValue, String data) {
		By locator = getLocators(locType, locValue);
		try {

			click(locType, locValue);
			// driver.switchTo().alert().sendKeys(data);
			Thread.sleep(2000);
			System.out.println(driver.switchTo().alert().getText());

			Robot rb = new Robot();

			rb.keyPress(KeyEvent.VK_D);
			rb.keyRelease(KeyEvent.VK_D);
			rb.keyPress(KeyEvent.VK_E);
			rb.keyRelease(KeyEvent.VK_E);
			rb.keyPress(KeyEvent.VK_M);
			rb.keyRelease(KeyEvent.VK_M);
			rb.keyPress(KeyEvent.VK_O);
			rb.keyRelease(KeyEvent.VK_O);

			Thread.sleep(2000);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);

//			Thread.sleep(2000);
//			click(locType, locValue);
////			Alert alert = driver.switchTo().alert();
//			Alert promptAlert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
//			Thread.sleep(2000);
////			String value = promptAlert.getText();
//			System.out.println(promptAlert.getText());
//			/*
//			 * JavascriptExecutor javascriptprompt = (JavascriptExecutor) driver;
//			 * javascriptprompt.executeScript("arguments[0].click();", promptAlert);
//			 */
//			promptAlert.sendKeys(data);
//			Thread.sleep(2000);
//			promptAlert.accept();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void click(String locType, String locValue) {
		By locator = getLocators(locType, locValue);
		driver.findElement(locator).click();
	}

	public Boolean verifyText(String locType, String locValue, String ExceptedResult) {
		By locator = getLocators(locType, locValue);
		String Actual = driver.findElement(locator).getText();
		Assert.assertEquals(Actual, ExceptedResult);
		// return Assert.assertTrue(condition, "The email address that you've entered
		// doesn't match any account. ");;
		return true;
	}

	public void closeBrowser() {
		driver.close();
	}
	/*
	 * public static void main(String args[]) throws IOException { GlobalFunctions
	 * gf = new GlobalFunctions(); gf.OpenBrowser(); gf.EnterText("id",
	 * "email","manasideodhar16@gmail.com"); gf.click("id", "u_0_2");
	 * gf.verifyText(locType, locValue, ExceptedResult)
	 * 
	 * }
	 */

}
