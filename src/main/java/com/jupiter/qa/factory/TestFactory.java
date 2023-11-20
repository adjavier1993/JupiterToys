package com.jupiter.qa.factory;

import com.jupiter.qa.testdata.TestData;

public final class TestFactory {
	
	private static ThreadLocal<TestData> testDataSet = new ThreadLocal<>();

	public static TestData initializeTestData() {
		testDataSet.set(new TestData());
		return getTestData();
	}

	public static TestData getTestData() {
		return testDataSet.get();
	}

}
