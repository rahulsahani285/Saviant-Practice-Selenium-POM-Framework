package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.LoginPage;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void doLogin() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority = 1)
	public void accPageTitle() {
		System.out.println(accPage.getAccountsPageTitle());
	}

	@Test(priority = 2)
	public void getCurrentURL() {
		System.out.println(accPage.getCurrentURL());
		AssertJUnit.assertTrue(accPage.getCurrentURL().contains(""));
	}

	@Test(priority = 3)
	public void accPageHeadersTest() {
		List<String> ActualaccPageHeadersList = accPage.getAccountPageHeaders();
		for (String s : ActualaccPageHeadersList) {
			System.out.println(ActualaccPageHeadersList);
		}
		AssertJUnit.assertEquals(ActualaccPageHeadersList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}

	@DataProvider
	public Object[][] getProductTestData() {

		return new Object[][] { { "Macbook", "Macbook Pro" }, { "Macbook", "Macbook Air" },
				{ "Apple", "Apple Cinema 30\"" }, { "iMac", "iMac" } };
	}

	@Test(dataProvider = "getProductTestData")
	public void searchOnAccountsPage(String searchKey, String productName) {
		searchPage = accPage.performSearch(searchKey);
		if (searchPage.getSearchedProductsCount() > 0) {
			productinfopage = searchPage.SelectProduct(productName);
		}
		String actProductHeader = productinfopage.getProductHeaderValue();
		AssertJUnit.assertEquals(actProductHeader, productName);
	}

}
