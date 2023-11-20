package com.qa.jupiter.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jupiter.qa.factory.DriverFactory;
import com.jupiter.qa.factory.TestFactory;
import com.jupiter.qa.pages.ContactsPage;
import com.jupiter.qa.util.Commons;

public class TestCase2 extends BaseTest {
	ContactsPage contactsPage = new ContactsPage(DriverFactory.getDriver(), TestFactory.getTestData());
	
	@Test
	public void Step1(){
		//Step 1 - From Home page go to Contacts page
		homePage.goToPage("Contact");
	}
	
	@Test(dataProvider="getData")
	public void Step2(String fieldname, String input) {
		//Step 2 - Populate mandatory fields
		contactsPage.populateField(fieldname, input);
	}
	
	@Test
	public void Step3() {
		//Step 3 - Click on Submit button
		contactsPage.clickSubmit();
	}
	
	@Test
	public void Step4() {
		//Step 4 - Validate successful submission message
		
		//wait for loading to disappear
		contactsPage.waitForLoading();
		//check success message
		contactsPage.assertSuccessMsg();
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data = Commons.getDataProvider("inputs");
		return data;
	}
}
