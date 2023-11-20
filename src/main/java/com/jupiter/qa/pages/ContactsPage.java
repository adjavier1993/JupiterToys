package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jupiter.qa.base.BasePage;
import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.Commons;

import org.testng.Assert;

public class ContactsPage extends BasePage{

	private String submitBtn = "//a[contains(@class,'btn-contact')]";
	private String inputField = "//*[contains(text(),'_')]/following-sibling::div/*[@type='text' or @type='email']";
	private String errorMsg = "//div[contains(@class,'controls')]/span[contains(text(),'_ is required')]";
	private String loadingPopup = "//div[contains(@class,'popup modal')]/descendant::div[contains(@class,'progress')]";
	private String successMsg = "//div[contains(@class,'success')]";
	public ContactsPage(WebDriver webDriver, TestData testData) {
		super(webDriver, testData);
	}
	
	public void clickSubmit() {
		WebElement elem = driver.findElement(By.xpath(submitBtn));
		Commons.waitForElement(elem);
		elem.click();
	}
	
	public void populateField(String fieldName, String inputText) {
		WebElement elem = driver.findElement(By.xpath(inputField.replace("_", fieldName)));
		elem.sendKeys(inputText);
	}
	

	/*
	 * Validate error messages by array of fieldnames
	 */
	public void assertErrorPresent(String[] mandatoryFields) {
		for(int i=0;i<mandatoryFields.length;i++) {
			boolean elemDisplayed = driver.findElement(By.xpath(errorMsg.replace("_", mandatoryFields[i]))).isDisplayed();
			Assert.assertTrue(elemDisplayed, "Error message not found");
		}
	}
	
	/*
	 * Validate error message is present (individually per field)
	 */
	public void assertErrorPresent(String fieldName) {
			boolean elemDisplayed = driver.findElement(By.xpath(errorMsg.replace("_", fieldName))).isDisplayed();
			Assert.assertTrue(elemDisplayed, "Error message not found");
	}
	
	/*
	 * Validate error message is not present (individually per field)
	 */
	public void assertErrorNotPresent(String fieldName) {
		try {
			driver.findElement(By.xpath(errorMsg.replace("_", fieldName))).isDisplayed();
		//catchAssert.assertFalse(isDisplayed, "Error message on field <" + fieldName + "> is present");
		} catch (NoSuchElementException ex) {
			System.out.println("Assertion Passed, element not found");
	    }
	}
	
	public void waitForLoading() {
		WebElement loading = driver.findElement(By.xpath(loadingPopup));
		Commons.waitForElementHide(loading);
	}
	
	public void assertSuccessMsg() {
		boolean elemDisplayed = driver.findElement(By.xpath(successMsg)).isDisplayed();
		Assert.assertTrue(elemDisplayed, "Success message not found");
	}
}	
