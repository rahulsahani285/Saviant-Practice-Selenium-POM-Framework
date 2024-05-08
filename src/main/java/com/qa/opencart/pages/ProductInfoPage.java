package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By ProductHeder = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By ProductMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][position()=1]/li");
	private By ProductPricing = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By assToCartBtn = By.id("button-cart");
	private By cartSuccessMessg=By.cssSelector("div.alert.alert-success");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeaderValue() {
		String ProductHeader = eleUtil.doElementGetText(ProductHeder);
		System.out.println("Produt header is: " + "" + ProductHeader);
		return ProductHeader;
	}

	public int getProductImageCount() {
		int count = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Number of images displayed are: " + "" + count);
		return count;
	}

	public Map getProductInfo() {
		//productInfoMap=new HashMap<String, String>();  // It Will store randomly
		
		productInfoMap=new LinkedHashMap<String, String>(); //To store the values in ordr the way we insert use LinkedHashMap
		
	//	productInfoMap=new TreeMap<String, String>(); // To store in Sorted Order using KEY, we can use TreeMap


		// Header:
		
		productInfoMap.put("productName", getProductHeaderValue());

		getPRoductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	public void getPRoductMetaData() {

//		Brand: Apple
//		Product Code: Product 15
//		Reward Points: 100
//		Availability: In Stock

		// Meta Data
		List<WebElement> metaList = eleUtil.getElements(ProductMetaData);
		for (WebElement e : metaList) {
			String meta = e.getText();
			String[] metaInfo = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
			// OR
			// productInfoMap.put(e.getText().split(":")[0], e.getText().split(":")[1]);
		}
	}

	public void getProductPriceData() {

		// $110.00
		// Ex Tax: $90.00
		// Price in reward points: 400

		// Pricing Data
		List<WebElement> priceList = eleUtil.getElements(ProductPricing);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();

		String s = exTax.split(":")[1].trim();
		productInfoMap.put("price", price);
		productInfoMap.put("Ex Tax", s);
	}
	
	
	public void enterQuantity(int qty)
	{
		eleUtil.doSendKeys(quantity,String.valueOf(qty));
	}
	
	public String addProductToCart()
	{
		eleUtil.doClick(assToCartBtn);
		String successMsg=eleUtil.waitForElementVisible(cartSuccessMessg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		return successMsg.substring(0,successMsg.length()-1);
	}

}
