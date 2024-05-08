package com.qa.opencart.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class testigtest {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream("C:\\Users\\rahul.n\\eclipse-workspace\\Selenium_Saviant_Framework\\src\\test\\resources\\Config\\config.properties");
		prop.load(fis);
		System.out.println(prop.getProperty("browser"));

	}

}









