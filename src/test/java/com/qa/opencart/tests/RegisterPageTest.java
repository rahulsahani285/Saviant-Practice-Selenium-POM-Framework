package com.qa.opencart.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelDataProvider;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetup()
	{
		registerPage=loginPage.navigateToRegisterPage();	
	}
	
	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName,String lastName,String email,String telephone,String password,String subscribe)
	{
		AssertJUnit.assertTrue(registerPage.registerUser(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}
	public String getRandomEmail()
	{
		Random ran=new Random();
		String email="Automation"+ran.nextInt(1000)+"@gmail.com";
		
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() throws Exception
	{
		Object RegData[][]=ExcelDataProvider.getTestData("register");
		return RegData;
	}
}
