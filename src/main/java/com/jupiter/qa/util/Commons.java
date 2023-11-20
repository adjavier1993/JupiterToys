package com.jupiter.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jupiter.qa.factory.TestFactory;
import com.jupiter.qa.testdata.TestData;

public class Commons {
	
	private static ConfigReader configReader;
	private static Properties prop;
	private static WebDriver driver;
	private TestData tstData;

	private static String TESTDATA_PATH = System.getProperty("user.dir") +"\\src\\main\\java\\com\\jupiter\\qa\\testdata\\TestData.xlsx";
	
	public Commons(WebDriver webDriver, TestData testData) {
		Commons.driver = webDriver;
		this.tstData = testData;
	}
	
	public Commons(WebDriver webDriver) {
		Commons.driver = webDriver;
	}
	
	public static void waitUntilElemIsClickable(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public static void waitUntilElemIsClickable(WebElement webElement) {
		FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public static void waitForElement(WebDriver driver, WebElement element) {
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class);

	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForElement(WebElement element) {
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class);

	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForElementHide(WebElement element) {
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(NoSuchElementException.class);

	    wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public static String getProp(String text) {
		configReader = new ConfigReader();
		prop = configReader.initProp();

		try {
			String config = prop.getProperty(text);
			return config.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	public static Object[][] getDataProvider(String sheetName){
		FileInputStream excelFile = null;
		XSSFWorkbook book = null;
		FormulaEvaluator evaluator = null;
		DataFormatter df = new DataFormatter();
		
		try{
			excelFile = new FileInputStream(TESTDATA_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = new XSSFWorkbook(excelFile);
			evaluator = book.getCreationHelper().createFormulaEvaluator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {
				Cell cell = sheet.getRow(i + 1).getCell(k);
				String text = df.formatCellValue(cell, evaluator);
				data[i][k] = text;
			}
		}
		
		return data;
	}
	
	/*
	 * Fetches the Item Names and Prices from Shopping Page
	 * Called from 
	 */
	public static void initDataItemPrice(String itemLocator, String priceLocator){
		TestData tstData = TestFactory.getTestData();
		List<ArrayList<String>> itemData = Commons.getAllItemAndPrice(itemLocator, priceLocator);

		ArrayList<String> name = itemData.get(0);
		ArrayList<String> price = itemData.get(1);
		
		for (int i = 0; i < name.size(); i++) {
			String itemName = name.get(i);
			String itemPrice = price.get(i);
			System.out.format("Item name: %s; Price: %s\n", itemName, itemPrice);
			tstData.setData(itemName, itemPrice);
		}
	}
	//Called by above method
	public static List<ArrayList<String>> getAllItemAndPrice(String itemLocator, String priceLocator){
		List<ArrayList<String>> testData = new ArrayList<ArrayList<String>>();
		ArrayList<String> itemNames = new ArrayList<>();
		ArrayList<String> itemPrices = new ArrayList<>();
		
		List<WebElement> itemElems = driver.findElements(By.xpath(itemLocator));
		for(WebElement elem : itemElems) {
			String elemText = elem.getText();
			//System.out.println("Element text fetched: "+elemText);
			itemNames.add(elemText);
		}
		
		List<WebElement> priceElems = driver.findElements(By.xpath(priceLocator));
		for(WebElement elem : priceElems) {
			String elemText = elem.getText();
			//System.out.println("Element text fetched: "+elemText);
			itemPrices.add(elemText);
		}
		
		testData.add(itemNames);
		testData.add(itemPrices);
		
		return testData;
	}
	
	public static Double getProduct(Double a, Double b) {
		return a*b;
	}
	
	public static Double sumDoubles(ArrayList<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }
	
}



