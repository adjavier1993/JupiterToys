package com.jupiter.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.jupiter.qa.base.BasePage;
import com.jupiter.qa.testdata.TestData;
import com.jupiter.qa.util.Commons;

public class CartPage extends BasePage{
	
	private String cartRowItemName = "//tbody/tr[contains(@class,'cart-item')]/td[1]";
	private String cartItemName = "//tbody/tr[contains(@class,'cart-item')]/td[contains(text(),'_')]";
	private String cartColLbl = "//tr/th[contains(text(),'_')]";
	private String cartColumns = "//tr/th";
	private String tableElem = "//tbody/tr[contains(@class,'cart-item')][%d]/td[%d]";
	private String totalValue = "//td/*[contains(@class,'total')]";
	
	public CartPage(WebDriver webDriver, TestData testData) {
		super(webDriver, testData);
	}
	
	public String getItemInfo(String itemName, String columnName) {
		List<WebElement> rows = driver.findElements(By.xpath(cartRowItemName));
		int rowIndex = 0;
		for(int i=0;i<rows.size();i++) {
			WebElement rowItemName = rows.get(i);
			//System.out.println("Row Item: " + rowItemName.getText());
			if (rowItemName.getText().equalsIgnoreCase(itemName)) {
				rowIndex = i+1;
			}
		}
		
		List<WebElement> columns = driver.findElements(By.xpath(cartColumns));
		int colIndex = 0;
		for(int i=0;i<columns.size();i++) {
			WebElement elem = columns.get(i);
			//System.out.println("Col Item: " + elem.getText());
			if (elem.getText().equalsIgnoreCase(columnName)) {
				colIndex = i+1;
			}
		}
		
		String tableInfo;
		String cellText;
		WebElement info;
		if(!columnName.equalsIgnoreCase("Quantity")) {
			tableInfo = String.format(tableElem, rowIndex, colIndex);
			info = driver.findElement(By.xpath(tableInfo));
			cellText = info.getText();
		}
		else {
			tableInfo = String.format(tableElem.concat("/input"), rowIndex, colIndex);
			info = driver.findElement(By.xpath(tableInfo));
			cellText = info.getAttribute("value");
		}
		
		return cellText;
		
	}
	
	/*
	 * Validate price of items on Shopping Cart individually
	 * @itemName - name of Item e.g. "Stuffed Frog"
	 */
	public void validatePrice(String itemName) {
		String expecPrice = tstData.getData(itemName);		
		String actualPrice = getItemInfo(itemName, "Price");
		
		Assert.assertTrue(expecPrice.equals(actualPrice));
		System.out.format("\n"+itemName+" with expected price of %s\n"
		+ "is equal to actual price of %s\n", expecPrice, actualPrice);
	}
	
	public void validateAllPrice() {
		List<WebElement> itemNameList = driver.findElements(By.xpath(cartRowItemName));
		for(int i=0;i<itemNameList.size();i++) {
			String itemName = itemNameList.get(i).getText();
			validatePrice(itemName);
		}
	}
	
	public void validateSubtotal(String itemName) {
		Double price = Double.valueOf(getItemInfo(itemName, "Price").substring(1));
		Double qty = Double.valueOf(getItemInfo(itemName, "Quantity"));
		Double expecSubtotal = Commons.getProduct(price, qty);
		
		Double subtotalToCheck = Double.valueOf(getItemInfo(itemName, "Subtotal").substring(1));
		
		String expectedSubtotal = "$".concat(String.format("%.2f", expecSubtotal));
		String actualSubtotal = "$".concat(String.format("%.2f", subtotalToCheck));
		
		Assert.assertTrue(expectedSubtotal.equals(actualSubtotal));
		System.out.format("\n"+itemName+" with Expected Subtotal of %s\n"
		+ "is equal to Actual Subtotal of %s", expectedSubtotal, actualSubtotal);
		
	}
	
	public void validateAllSubtotal() {
		List<WebElement> itemNameList = driver.findElements(By.xpath(cartRowItemName));
		for(int i=0;i<itemNameList.size();i++) {
			String itemName = itemNameList.get(i).getText();
			validateSubtotal(itemName);
		}
	}
	
	public void validateTotalPrice() {
		WebElement totalElem = driver.findElement(By.xpath(totalValue));
		double expecTotal = Double.valueOf(totalElem.getText().split(" ")[1]);
		
		//get the sum of the subtotals
		List<WebElement> itemNameList = driver.findElements(By.xpath(cartRowItemName));
		ArrayList<Double> subtotalList = new ArrayList<Double>();
		for(int i=0;i<itemNameList.size();i++) {
			String itemName = itemNameList.get(i).getText();
			Double subtotal = Double.valueOf(getItemInfo(itemName, "Subtotal").substring(1));
			subtotalList.add(subtotal);
		}
		double actualTotal = Commons.sumDoubles(subtotalList);
		
		Assert.assertEquals(expecTotal, actualTotal);
		System.out.format("\nExpected Total of %.2f\n"
				+ "is equal to Actual Total of %.2f", expecTotal, actualTotal);
	}

}
