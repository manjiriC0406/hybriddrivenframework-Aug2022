package com.tc.orghrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tc.orghrm.base.PreDefinedActions;

public class LoginPage extends PreDefinedActions {
	
	//Page Factory Model: Can write all 8 locator strategies here
	//It is lazy loading, The moment we perform any action on the element, driver will find that element on page
	//Limitation: Dynamic elements cannot be found using Page Factory Model
	@FindBy(xpath = "//input[@name='txtUsername']") 
	private WebElement userNameElement;
	
	@FindBy(xpath ="//input[@name='txtPassword']")
	private WebElement passwordElement;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;
	
	@FindBy (css = "#txtUsername-error")
	private WebElement usernameErrorElement;
	
	@FindBy (css = "#txtPassword-error")
	private WebElement passwordErrorElement;
	
	@FindBy (css = "div.organization-logo.shadow>img")
	private WebElement logo;
	
	public LoginPage() {
		PageFactory.initElements(driver,this); //elements are initialized not searched at this time, lazy loading concept
		//if init element not called then NoSuchElementException, we can write this in non-static block as well
	}
	
	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginBtn();
	}
	
	public void enterUsername(String username) {
		//driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys(username); //1st approach, not recommended
		
		//WebElement e = getElement("xpath", "//input[@name='txtUsername']", false);
		//setText(e, username); //2nd approach
		
		//setText("xpath", "//input[@name='txtUsername']", false, username); //3rd approach
		
		//userNameElement.sendKeys(username); // 4th approach
		
		setText(userNameElement, username); //5th approach
	}
	
	public void enterPassword(String password) {
		//driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys(password);
		setText(passwordElement, password);
	}
	
	public void clickOnLoginBtn() {
		//driver.findElement(By.xpath("//button[@type='submit']")).click();
		clickOnElement(submitButton, false);
	}
	
	public boolean isUsernameErrorMessageDisplayed() {
		//WebElement usernameErrorMessage = driver.findElement(By.cssSelector("#txtUsername-error"));
		//isDisplayed() searches element on webpage not on DOM, it searches on Viewport
		//If element not on DOM, NoSuchElementException
		//return usernameErrorMessage.isDisplayed(); 
		return isElementDisplayed(usernameErrorElement);
	}
	
	public boolean isPasswordErrorMessageDisplayed() {
		//WebElement passwordErrorMessage = driver.findElement(By.cssSelector("#txtPassword-error"));
		//return passwordErrorMessage.isDisplayed();
		return isElementDisplayed(passwordErrorElement);
	}
	
	public boolean isLogoDisplayed() {
		//WebElement logoCheck = driver.findElement(By.cssSelector("div.organization-logo.shadow>img"));
		//return logoCheck.isDisplayed();
		return isElementDisplayed(logo);
	}
}
