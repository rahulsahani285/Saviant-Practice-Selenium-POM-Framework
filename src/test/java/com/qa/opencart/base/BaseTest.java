package com.qa.opencart.base;

import org.testng.annotations.AfterMethod;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productinfopage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;

	@BeforeTest
	public void setup() throws IOException {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		System.out.println(prop.getProperty("usernmame"));

		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
	}

	@AfterMethod
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
