package com.amazon.amazonapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class AmazonLibrary {
	public AndroidDriver startApplication() throws MalformedURLException {
		URL URL = new URL("http://localhost:4723/wd/hub");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
		capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");

		AndroidDriver driver = new AndroidDriver(URL, capabilities);
		System.out.println("Session ID is " + driver.getSessionId());

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		List <WebElement> cancel = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/btn_cancel"));
		if (cancel.size()>0) {
			

			// WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(10));
			// wc.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.amazon.mShop.android.shopping:id/btn_cancel")));
			driver.findElement(By.id("com.amazon.mShop.android.shopping:id/btn_cancel")).click();
			
		}
		else
		{
			
		List <WebElement> skip_signin = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		
		
		if (skip_signin.size()>0)
		{
			driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();

		}
		List <WebElement> secondskip_signin = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		if (secondskip_signin.size()>0)
		{
			driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();

		}
		
		}
		return driver;

	}

	public void clickSearch(AndroidDriver driver, String productname) {
		List <WebElement> skip_signin = driver.findElements(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		if (skip_signin.size()>0)
		{
			driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();

		}

		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/chrome_search_hint_view")).click();
		WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(10));
		wc.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")));
		WebElement search = driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text"));
				search.sendKeys(productname);
		//driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
		Actions keyboard = new Actions(driver);
		keyboard.keyDown(search,Keys.ENTER).build().perform();
		driver.hideKeyboard();

	}

	public void getAllproducts(AndroidDriver driver) {

		List<WebElement> allproducts = driver.findElements(By.xpath("//android.widget.Button[@index='1']"));
		System.out.println("****************List of products are listed below**************** ");
		
		for(WebElement product:allproducts)
		{
			
			System.out.println(product.getText());
			/*
			if (product.getText().equals("airpods pro")) {
				product.click();
				break;
				
			}
			*/
		}
		
		WebElement airpod_pro = allproducts.get(3);
		airpod_pro.click();
		
		System.out.println("Search result are displayed below ");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// xpath =//android.view.View[@resource-id='search']/android.view.View/android.view.View[@index=4 and @content-desc]
		List<WebElement> search_results = driver.findElements(By.xpath("//android.view.View[@resource-id='search']/android.view.View/android.view.View[@index=3 and @content-desc]"));
		System.out.println(search_results.size());
		for(WebElement result:search_results)
		{
			
			System.out.println(result.getAttribute("text"));
			System.out.println(result.getText());
			System.out.println(result);
		}

	}

}
