package advanto.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	public String getData(String key) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\advanto\\framework\\config.properties");
		Properties p = new Properties();
		p.load(fileInputStream);
		return p.getProperty(key);
	}

}