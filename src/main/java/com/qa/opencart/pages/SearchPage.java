package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By ProductsDisplayed=By.xpath("//div[@class='caption']");
	private int ProductsCount;

//	private By Product = By.linkText("MacBook Pro");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	public ProductInfoPage SelectProduct(String productName) {
		By productLocator= By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
	
	public int getSearchedProductsCount()
	{
		ProductsCount=eleUtil.waitForElementsVisible(ProductsDisplayed, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Count of Products Displayed related to searched keyword are:"+""+ProductsCount);
		
		return ProductsCount;
	}

}
