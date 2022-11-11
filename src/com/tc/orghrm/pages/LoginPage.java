package com.tc.orghrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tc.orghrm.base.PreDefinedActions;

public class LoginPage extends PreDefinedActions {
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
	}
	
	public void enterUsername(String username) {
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys(password);
	}
	
	public void clickOnLoginBtn() {
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public boolean isUsernameErrorMessageDisplayed() {
		WebElement usernameErrorMessage = driver.findElement(By.cssSelector("#txtUsername-error"));
		return usernameErrorMessage.isDisplayed();
	}
	
	public boolean isPasswordErrorMessageDisplayed() {
		WebElement passwordErrorMessage = driver.findElement(By.cssSelector("#txtPassword-error"));
		return passwordErrorMessage.isDisplayed();
	}
	
	public boolean isLogoDisplayed() {
		WebElement logoCheck = driver.findElement(By.cssSelector("div.organization-logo.shadow>img"));
		return logoCheck.isDisplayed();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
}
