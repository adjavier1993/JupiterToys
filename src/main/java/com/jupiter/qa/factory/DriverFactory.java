package com.jupiter.qa.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jupiter.qa.util.Commons;


public class DriverFactory {
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver initializeDriver() {
		String browserName = Commons.getProp("browser");
		String chromeDriverPath = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			tlDriver.set(new ChromeDriver());
		}
		getDriver().get(Commons.getProp("url"));
		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return getDriver();
	}
	

	/**
	 * This is used to get the driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
}
