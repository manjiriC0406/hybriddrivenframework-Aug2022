package com.tc.orghrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tc.orghrm.base.PreDefinedActions;
import com.tc.orghrm.pages.LoginPage;

public class LoginTest {
	
	@Test
	public void tc1() {
		System.out.println("Step: Launch Browser and hit the URL");
		PreDefinedActions.start("https://mchourikar-trials77.orangehrmlive.com/");
		
		System.out.println("Step: Enter Login Credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "ATaA@dc1N8");
		
		System.out.println("Verify: Home page is displsyed");
		String expectedTitle = "Employee Management";
		String actualTitle = loginPage.getPageTitle();
		//Hard Assert
		Assert.assertEquals(actualTitle, expectedTitle, "Expected title was" + expectedTitle + "but actual title was" + actualTitle);
		
		System.out.println("Close Browser");
		PreDefinedActions.closeBrowser();
	}
}
