package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import com.qa.opencart.base.BaseTest;

import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop = new Properties();
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This Method is initilizing the driver
	 * 
	 * @param browsername
	 * @return it return the Driver
	 */
	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");
		String BrowserName = prop.getProperty("browser").trim().toLowerCase();

		System.out.println("Your Browser name is: " + "" + BrowserName);

		if (BrowserName.trim().equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}

		else if (BrowserName.trim().equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}

		else if (BrowserName.trim().equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();

	}

	/**
	 * Reading the property in key value pair from propeties file.
	 * 
	 * @return Properties object containing key value pair
	 */

	public Properties initProp() {
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\rahul.n\\eclipse-workspace\\Selenium_Saviant_Framework\\src\\test\\resources\\Config\\config.properties");
			prop.load(fis);

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	public static String getScreenshot() 
	{
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String destFile=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		
		try {
		FileUtils.copyFile(srcFile, new File(destFile));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return destFile;
	}

}
