package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By lgnbutton = By.xpath("//input[@class='btn btn-primary']");
	private By forgotpwdlink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	public String getloginPageTitle() {
		String title =eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, "Account Login");
		System.out.println("Current Page title: "+""+ driver.getCurrentUrl());
		return title;
	}

	public String getloginPageURL() {
		
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, "route=account/login");
		System.out.println("Login Page URL: "+""+url);
		return url;
	}

	public Boolean forgetpwdlinkDisplayed() {
		Boolean bool = driver.findElement(forgotpwdlink).isDisplayed();

		return bool;
	}

	public AccountsPage doLogin(String un, String pwd) throws InterruptedException {
		eleUtil.waitForElementPresence(email, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);

		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(lgnbutton);
		
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		
		return new RegisterPage(driver);
	}

}
