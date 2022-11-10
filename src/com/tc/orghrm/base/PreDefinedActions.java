
//classname can be WebExtensibility, Helper, CommonMethods

package com.tc.orghrm.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PreDefinedActions {
	protected static WebDriver driver;
	
	public static void start(String url) {
		System.setProperty("webdriver.com.driver", "drivers/chromedriver_106.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void closeBrowser() {
		driver.close();
	}
}
