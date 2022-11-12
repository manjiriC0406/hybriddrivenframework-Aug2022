package com.tc.orghrm.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tc.orghrm.base.PreDefinedActions;
import com.tc.orghrm.pages.LoginPage;
import com.tc.orghrm.utility.ExcelOperations;

public class LoginTest {
	
	@Test(dataProvider = "LoginDataProvider")
	public void tc1(String url, String uname, String password, boolean isLoginSuccessful) {
		System.out.println("Step: Launch Browser and hit the URL");
		PreDefinedActions.start(url);
		
		System.out.println("Step: Enter Login Credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login(uname, password);
		
		if(isLoginSuccessful) {
			System.out.println("Verify: Home page is displsyed");
			String expectedTitle = "Employee Management";
			String actualTitle = loginPage.getPageTitle();
			//Hard Assert
			Assert.assertEquals(actualTitle, expectedTitle, "Expected title was" + expectedTitle + "but actual title was" + actualTitle);
		}else {
			System.out.println("Verify: Home page is displsyed");
			String expectedTitle = "OrangeHRM";
			String actualTitle = loginPage.getPageTitle();
			//Hard Assert
			Assert.assertEquals(actualTitle, expectedTitle, "Expected title was" + expectedTitle + "but actual title was" + actualTitle);
			
			System.out.println("Verify: Retry Login Page is loaded");
			String expectedURLContent = "retryLogin";
			String actualCurrentURL = loginPage.getPageURL();
			Assert.assertTrue(actualCurrentURL.endsWith(expectedURLContent));
		}
				
		System.out.println("Close Browser");
		PreDefinedActions.closeBrowser();
	}
	
	@DataProvider(name = "LoginDataProvider")
	Object[][] getLoginData() throws IOException{
		Object[][] data = ExcelOperations.readExcelData(".//testdata//LoginData.xlsx", "Data");
		return data;
	}
	
	@DataProvider(name = "LoginDataProvider1")
	Object[][] getLoginData1(){
		Object[][] data = new Object[2][4];
		data[0][0] = "https://mchourikar-trials77.orangehrmlive.com/";
		data[0][1] = "Admin";
		data[0][2] = "ATaA@dc1N8";
		data[0][3] = true;
		
		data[1][0] = "https://mchourikar-trials77.orangehrmlive.com/";
		data[1][1] = "Admin";
		data[1][2] = "ATaA@dc1N812";
		data[1][3] = false;
		
		return data;
	}
	
//	@DataProvider
//	Object[][] getSignupData(){
//		return null;
//	}
	
	@Test
	public void tc1_Negative() {
		System.out.println("Step: Launch Browser and hit the URL");
		PreDefinedActions.start("https://mchourikar-trials77.orangehrmlive.com/");
		
		System.out.println("Step: Enter Login Credentials");
		LoginPage loginPage = new LoginPage();
		loginPage.login("Admin", "ATaA@dc1N812");
		
		System.out.println("Verify: Home page is displsyed");
		String expectedTitle = "OrangeHRM";
		String actualTitle = loginPage.getPageTitle();
		//Hard Assert
		Assert.assertEquals(actualTitle, expectedTitle, "Expected title was" + expectedTitle + "but actual title was" + actualTitle);
		
		System.out.println("Verify: Retry Login Page is loaded");
		String expectedURLContent = "retryLogin";
		String actualCurrentURL = loginPage.getPageURL();
		Assert.assertTrue(actualCurrentURL.endsWith(expectedURLContent));
		
		System.out.println("Clean up - Close Browser");
		PreDefinedActions.closeBrowser();
	}
}
