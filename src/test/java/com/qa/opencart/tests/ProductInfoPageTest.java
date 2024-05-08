package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() throws InterruptedException {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {

		return new Object[][] { { "Macbook", "MacBook Pro" , 4}, { "Samsung", "Samsung SyncMaster 941BW" , 1},
				{ "Apple", "Apple Cinema 30\"", 6 }, { "iMac", "iMac",3 } };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int ImagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productinfopage = searchPage.SelectProduct(productName);
		int imgCount = productinfopage.getProductImageCount();
		AssertJUnit.assertEquals(imgCount, ImagesCount);
	}
	
	@Test
	public void productInfoTest()
	{
		searchPage = accPage.performSearch("MacBook");
		productinfopage = searchPage.SelectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap= productinfopage.getProductInfo();
		System.out.println(actProductInfoMap);
		AssertJUnit.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		AssertJUnit.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		AssertJUnit.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		AssertJUnit.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		
		softAssert.assertAll();
		
	}
	
	@Test
	public void addToCart()
	{
		searchPage = accPage.performSearch("MacBook");
		productinfopage = searchPage.SelectProduct("MacBook Pro");
		productinfopage.enterQuantity(2);
		String actSuccessMsg=productinfopage.addProductToCart();
	//	Success: You have added iMac to your shopping cart!
		AssertJUnit.assertTrue(actSuccessMsg.contains("Success"));

		AssertJUnit.assertTrue(actSuccessMsg.contains("MacBook Pro"));
	System.out.println(actSuccessMsg.trim());
		AssertJUnit.assertEquals(actSuccessMsg.trim(),"Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
	}

}
