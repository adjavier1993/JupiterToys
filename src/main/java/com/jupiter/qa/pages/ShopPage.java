package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jupiter.qa.base.BasePage;
import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.Commons;

public class ShopPage extends BasePage {
	//private WebDriver driver;
	private String itemNames = "//div[contains(@class,'products')]//li[contains(@class,'product')]//h4";
	private String itemPrices = "//div[contains(@class,'products')]//li[contains(@class,'product')]//span";
	private String buyButton = "//h4[contains(text(),'_')]/following-sibling::p/a";
	
	public ShopPage(WebDriver webDriver, TestData testData) {
		super(webDriver, testData);
	}
	
	/*
	 * Click the Buy button that belongs to <itemName>
	 * by <qty> times
	 */
	public void buyItem(String itemName, String qty) {
		WebElement buyBtn = driver.findElement(By.xpath(buyButton.replace("_", itemName)));
		Commons.waitForElement(buyBtn);
		
		for(int i=0;i<Integer.valueOf(qty);i++) {
			Commons.waitUntilElemIsClickable(buyBtn);
			buyBtn.click();
		}
	}
	
	public void initItemAndPriceList() {
		Commons.initDataItemPrice(itemNames, itemPrices);
	}
	
	
}
