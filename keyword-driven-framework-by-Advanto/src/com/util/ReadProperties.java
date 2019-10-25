package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public String getValue(String key) throws IOException {
		FileInputStream propertiesFile = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\com\\data\\config.properties");
		Properties properties = new Properties();
		properties.load(propertiesFile);
		return properties.getProperty(key);
	}
}