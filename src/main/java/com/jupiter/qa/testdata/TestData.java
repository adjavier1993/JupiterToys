package com.jupiter.qa.testdata;

import java.util.HashMap;
import java.util.Map;

public class TestData {
	
	private Map<String, String> testData = new HashMap<>(1000);
	
	/**
	 * @description get data using keyname
	 */
	public String getData(String key) {
		return testData.get(key);
	}
	
	/**
	 * @description set data - key and value
	 */
	public void setData(String key, String value) {
		testData.put(key, value);
	}
	
	/**
	 * @description check if key exists
	 */
	public Boolean isKeyExist(String key) {
		return testData.containsKey(key);
	}
}