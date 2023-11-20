package com.qa.jupiter.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jupiter.qa.factory.DriverFactory;
import com.jupiter.qa.factory.TestFactory;
import com.jupiter.qa.pages.CartPage;
import com.jupiter.qa.pages.ShopPage;
import com.jupiter.qa.util.Commons;

public class TestCase3 extends BaseTest{
	
	ShopPage shopPage = new ShopPage(DriverFactory.getDriver(), TestFactory.getTestData());
	CartPage cartPage = new CartPage(DriverFactory.getDriver(), TestFactory.getTestData());
	
	
	@Test(dataProvider="getData")
	public void Step1(String itemName, String qty) {
		//Navigate to shopping page
		homePage.goToPage("Shop");
		
		//Step 1 - purchase items
		shopPage.buyItem(itemName, qty);

	}
	@Test
	public void Step2() {
		//Prepare Item Name and Price reference from Shopping Page
		shopPage.initItemAndPriceList();
		
		//Proceed to Shopping Cart
		homePage.goToPage("Cart");
	}
	
	@Test
	public void Step3() {
		//Validate item Price on Shopping Cart
		cartPage.validateAllPrice();
	}
	
	@Test
	public void Step4() {
		//Validate Subtotal value on Shopping Cart
		cartPage.validateAllSubtotal();
	}
	
	@Test
	public void Step5() {
		//Validate Total value on Shopping Cart
		cartPage.validateTotalPrice();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data = Commons.getDataProvider("TC3");
		return data;
	}
}
