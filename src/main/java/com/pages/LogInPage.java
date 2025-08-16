package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.base.BasePage;

public class LogInPage extends BasePage {

	@FindBy(xpath = "//input[@type='email']")
	public WebElement userNameTextBox;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement passwordTextBox;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement logInButton;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String username) {
		userNameTextBox.clear();
		userNameTextBox.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);

	}

	public void clickLogIn() {
		logInButton.click();
		Assert.assertEquals(driver.getTitle(), "admin-demo.nopcommerce.com");

	}

}
