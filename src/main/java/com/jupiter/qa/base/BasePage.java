package com.jupiter.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.Commons;

public class BasePage {
	
	protected static WebDriver driver;
	protected static TestData tstData;
	public Commons commons;
	
	public BasePage(WebDriver webDriver, TestData testData) {
		BasePage.driver = webDriver;
		BasePage.tstData = testData;
		this.commons = new Commons(webDriver, testData);
	}
	
//	public static void initialize() {
//		String browserName = prop.getProperty("browser");
////		String browserName = "chrome";
//		if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver","C:\\Users\\JLegaspi\\chromedriver\\chromedriver.exe");
//			driver = new ChromeDriver();
//		}
//		driver.get(prop.getProperty("url"));
//		
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//	}
}
