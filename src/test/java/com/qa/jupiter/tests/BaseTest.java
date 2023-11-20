package com.qa.jupiter.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.jupiter.qa.factory.DriverFactory;
import com.jupiter.qa.factory.TestFactory;
import com.jupiter.qa.pages.HomePage;
import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.ConfigReader;


public class BaseTest{
	
	
	/*
	 * testing lang tong mga to
	 */
	private DriverFactory driverFactory;
	private WebDriver driver;
	private TestData tstData;
	private ConfigReader configReader;
	private Properties prop;
	HomePage homePage;

	
	@BeforeTest
	public void setUp() {

		try {
			driverFactory = new DriverFactory();
			driver = driverFactory.initializeDriver();
			tstData = TestFactory.initializeTestData();
			homePage = new HomePage(DriverFactory.getDriver(), TestFactory.getTestData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
	