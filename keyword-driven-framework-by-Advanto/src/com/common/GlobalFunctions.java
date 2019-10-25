package com.common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.util.ReadProperties;

public class GlobalFunctions {

	WebDriver driver;
	ReadProperties properties;

	public void openBrowser() throws IOException {
		properties = new ReadProperties();
		String browser = properties.getValue("brower");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + properties.getValue("browser_path"));
			driver = new ChromeDriver();
		}
	}

}