package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.utils.ExtentReportManager;
import com.utils.Log;

public class BasePage {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setUpReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setUp() {
		Log.info("Starting WebDriver....");
		driver = new ChromeDriver();
		Log.info("Navigating to URL....");
		driver.get("https://admin-demo.nopcommerce.com/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, result.getName());
			System.out.println("Screenshot captures, PATH: " + screenshotPath);
			test.fail("Test Failed. Screenshot attached",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		if (driver != null)
			Log.info("Closing the browser....");
		driver.quit();
	}
}
