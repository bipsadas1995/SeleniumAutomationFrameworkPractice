package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.pages.LogInPage;
import com.utils.ExcelUtils;
import com.utils.ExtentReportManager;
import com.utils.Log;

public class LogInTest extends BasePage {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		String filepath = System.getProperty("user.dir") + "/testdata/testdata.xlsx";
		ExcelUtils.loadExcel(filepath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0);
			data[i - 1][1] = ExcelUtils.getCellData(i, 1);
		}
		ExcelUtils.closeExcel();
		return data;
	}

	@Test(dataProvider = "LoginData")
	public void testValidLogIn(String username, String password) {
		LogInPage loginpage = new LogInPage(driver);

		test = ExtentReportManager.createTest("Login Test: " + username);
		test.info("Navigating to URL");
		Log.info("Entering Username");
		// loginpage.enterUserName("admin@yourstore.com");
		loginpage.enterUserName(username);
		Log.info("Entering Password");
		// loginpage.enterPassword("admin");
		loginpage.enterPassword(password);
		test.info("Clicking on Login button");
		loginpage.clickLogIn();
		System.out.println("Title of the page: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
		test.pass("Login Successful");
	}

	@Test(dataProvider = "LoginData")
	public void testInvalidCredential(String username, String password) {
		LogInPage loginpage = new LogInPage(driver);

		test = ExtentReportManager.createTest("Login Test: " + username);
		test.info("Navigating to URL");
		Log.info("Entering Username");
		// loginpage.enterUserName("admin@yourstore.com");
		loginpage.enterUserName(username);
		Log.info("Entering Password");
		// loginpage.enterPassword("admin1");
		loginpage.enterPassword(password);
		test.info("Clicking on Login button");
		loginpage.clickLogIn();
		System.out.println("Title of the page: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration  ");
		test.pass("Login Successful");
	}

}
