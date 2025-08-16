package com.tests;

import org.testng.annotations.Test;

import com.base.BasePage;
import com.pages.LogInPage;

public class LogInTest extends BasePage {

	@Test
	public void testValidLogIn() {
		LogInPage loginpage = new LogInPage(driver);
		loginpage.enterUserName("admin@yourstore.com");
		loginpage.enterPassword("admin");
		loginpage.clickLogIn();
		System.out.println("Title of the page: " + driver.getTitle());
	}

}
