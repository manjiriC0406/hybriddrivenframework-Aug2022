
//classname can be WebExtensibility, Helper, CommonMethods

package com.tc.orghrm.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tc.orghrm.customexceptions.ElementNotEnabledException;

public class PreDefinedActions {
	protected static WebDriver driver;
	static WebDriverWait wait;
	
	protected PreDefinedActions() {
		
	}
	
	public static void start(String url) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_106.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60); //polling time is 500 ms
	}
	
	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;
		switch(locatorType.toLowerCase()) {
		case "id":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue))); 
				//visibilityOfElementLocated() has responsiblity to scroll to that element
			}else {
				driver.findElement(By.id(locatorValue));
			}
			break;
			
		case "xpath":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			}else {
				driver.findElement(By.xpath(locatorValue));
			}
			break;
			
		case "cssselector":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			}else {
				driver.findElement(By.cssSelector(locatorValue));
			}
			break;
			
		case "name":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else {
				driver.findElement(By.name(locatorValue));
			}
			break;
			
		case "linktext":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			}else {
				driver.findElement(By.linkText(locatorValue));
			}
			break;
			
		case "partiallinktext":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			}else {
				driver.findElement(By.partialLinkText(locatorValue));
			}
			break;
			
		case "classname":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			}else {
				driver.findElement(By.className(locatorValue));
			}
			break;
			
		case "tagname":
			if(isWaitRequired) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			}else {
				driver.findElement(By.tagName(locatorValue));
			}
			break;
		}
		return element;
	}
	
	//fluent wait required here
	protected void setText(WebElement e, String text) {
		scrollToElement(e);
		if(e.isEnabled()) {
			e.sendKeys(text);
		}
		else {
			throw new ElementNotEnabledException(text + "cannot be entered as element not enabled");
		}
	}
	
	protected void setText(String locatorType, String locatorValue, boolean isWaitRequired, String text) {
		WebElement e = getElement(locatorType, locatorValue, isWaitRequired);
		scrollToElement(e);
		if(e.isEnabled()) {
			e.sendKeys(text);
		}
		else {
			throw new ElementNotEnabledException(text + "cannot be entered as element not enabled");
		}
	}
	
	protected void clickOnElement(WebElement e, boolean isWaitRequiredBeforeClick) {
		scrollToElement(e);
		if(isWaitRequiredBeforeClick) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		e.click();
	}
	
	protected void scrollToElement(WebElement e) {
		if(!e.isDisplayed()) {
			JavascriptExecutor  je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true)", e);
		}
	}
	
	protected boolean isElementDisplayed(WebElement e) {
		scrollToElement(e);
		
		return e.isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public static void closeBrowser() {
		driver.close();
	}
}
