package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getloginPageTitle();
		AssertJUnit.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getloginPageURL();
		// Assert.assertEquals(actualURL,
		// "https://www.naveenautomationlabs.com/opencart/index.php?route=account/login");
		AssertJUnit.assertTrue(actualURL.contains("route=account/login"));
	}

	@Test(priority = 3)
	public void forgotpwdlinkdisplayed() {
		Assert.assertTrue(loginPage.forgetpwdlinkDisplayed());
	}

	@Test(priority = 4)
	public void dologin() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username").trim(),this.prop.getProperty("password").trim());
	}
	

}
