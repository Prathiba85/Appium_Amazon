package com.amazon.amazonapp;

import java.net.MalformedURLException;



import io.appium.java_client.android.AndroidDriver;

public class TestCases {

	public static void main(String[] args) throws MalformedURLException {
		AmazonLibrary ab = new AmazonLibrary();
		AndroidDriver driver = ab.startApplication();
		ab.clickSearch(driver, "air pods");
		ab.getAllproducts(driver);

	}
}
