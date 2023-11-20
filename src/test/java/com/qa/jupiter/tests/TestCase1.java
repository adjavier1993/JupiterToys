package com.qa.jupiter.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jupiter.qa.pages.ContactsPage;
import com.jupiter.qa.util.Commons;
import com.jupiter.qa.factory.DriverFactory;
import com.jupiter.qa.factory.TestFactory;

public class TestCase1 extends BaseTest{

	ContactsPage contactsPage = new ContactsPage(DriverFactory.getDriver(), TestFactory.getTestData());
	
	@Test
	public void Step1(){
		//Step 1 - Go to contacts page
		homePage.waitForBanner();
		homePage.goToPage("Contact");
	}
	
	@Test
	public void Step2() {
		//Step 2 - Click on Submit button
		contactsPage.clickSubmit();
	}
	
	@Test
	public void Step3() {
		//Step 3 - Validate mandatory field error messages
		contactsPage.waitForHeaderMsg();
		contactsPage.assertErrorPresent("Forename");
		contactsPage.assertErrorPresent("Email");
		contactsPage.assertErrorPresent("Message");
	}
	
	@Test(dataProvider="getData")
	public void Step4(String fieldname, String input) {
		//Step 2 - Populate mandatory fields
		contactsPage.populateField(fieldname, input);
	}
	
	@Test
	public void Step5() {
		//Step 5 - Verify that error messages are gone after populating mandatory fields
		contactsPage.assertErrorNotPresent("Forename");
		contactsPage.assertErrorNotPresent("Email");
		contactsPage.assertErrorNotPresent("Message");
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data = Commons.getDataProvider("inputs");
		return data;
	}
}
