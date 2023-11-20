package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jupiter.qa.base.BasePage;
import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.Commons;

public class HomePage extends BasePage{
	//private WebDriver driver;
	
	private String menuBtn = "//a[contains(text(),'_')]";
	private String jupiterBanner = "//div[contains(@class,'hero-unit')]";
	
	public HomePage(WebDriver webDriver, TestData testData) {
		super(webDriver, testData);
	}
	
	public void openJupiterWebsite() {
		driver.get(Commons.getProp("url"));
	}
	
	/*
	 * Go to page based on <pageName>
	 */
	public void goToPage(String pageName) {
		WebElement elem = driver.findElement(By.xpath(menuBtn.replace("_", pageName)));
		Commons.waitUntilElemIsClickable(elem);
		elem.click();
	}
	
	public void waitForBanner() {
		WebElement elem = driver.findElement(By.xpath(jupiterBanner));
		Commons.waitForElement(elem);
	}

}

	