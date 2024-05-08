package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutlink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchBox = By.xpath("//div[@id='search']/input");
	private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getAccountsPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public Boolean logoutLinkDisplayed() {
		return eleUtil.waitForElementVisible(logoutlink, 10).isDisplayed();

	}

	public Boolean isSearchExists() {

		return eleUtil.waitForElementVisible(search, 10).isDisplayed();
	}

	public List<String> getAccountPageHeaders() {
		List<WebElement> Headers = eleUtil.waitForElementsVisible(accHeaders, 10);
		List<String> accHeadersValue = new ArrayList<String>();

		for (WebElement w : Headers) {
			accHeadersValue.add(w.getText());

		}
		return accHeadersValue;
	}

	public SearchPage performSearch(String searchKey) {
		if (driver.findElement(searchBox).isDisplayed()) {
			eleUtil.doSendKeys(searchBox, searchKey);
			eleUtil.doClick(searchButton);
			return new SearchPage(driver);

		}
		else {
			System.out.println("Search Field is not present");
			return null;
		}
	}
}
