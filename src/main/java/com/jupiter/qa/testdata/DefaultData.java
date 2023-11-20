package com.jupiter.qa.testdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.jupiter.qa.factory.DriverFactory;
import com.jupiter.qa.factory.TestFactory;
import com.jupiter.qa.util.Commons;


public class DefaultData {
	
	//private static WebDriver driver = DriverFactory.getDriver();
	
	public static void initDataItemPrice(String itemLocator, String priceLocator) throws Exception {
		TestData tstData = TestFactory.getTestData();
		List<ArrayList<String>> itemData = Commons.getAllItemAndPrice(itemLocator, priceLocator);

		ArrayList<String> name = itemData.get(0);
		ArrayList<String> price = itemData.get(1);
		
		for (int i = 0; i < name.size(); i++) {
			String itemName = name.get(i);
			String itemPrice = price.get(i);
			
			tstData.setData(itemName, itemPrice);
			
		}
	}
	
//	public static List<ArrayList<String>> getDataFromExcel(String sheetName){
//		List<ArrayList<String>> testData = new ArrayList<ArrayList<String>>();
//		ArrayList<String> itemNames = new ArrayList<>();
//		ArrayList<String> itemPrices = new ArrayList<>();
//		
//		List<WebElement> itemElems = driver.findElements(By.xpath(itemLocator));
//		for(WebElement elem : itemElems) {
//			String elemText = elem.getText();
//			System.out.println("Element text fetched: "+elemText);
//			itemNames.add(elemText);
//		}
//		
//		List<WebElement> priceElems = driver.findElements(By.xpath(priceLocator));
//		for(WebElement elem : priceElems) {
//			String elemText = elem.getText();
//			System.out.println("Element text fetched: "+elemText);
//			itemPrices.add(elemText);
//		}
//		
//		testData.add(itemNames);
//		testData.add(itemPrices);
//		
//		return testData;
//	}
	
}
