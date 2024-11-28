package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

	protected WebDriver driver;
	
	
	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By postalCode = By.id("postal-code");
	By continueBtn = By.id("continue");
	By checkoutText = By.className("title");
	
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterUserDetails(String fName, String Lname, String zipCode) {
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(Lname);
		driver.findElement(postalCode).sendKeys(zipCode);
		driver.findElement(continueBtn).click();
	}
	
	public String verifyCheckOutPageText() {
		return driver.findElement(checkoutText).getText();
	}

}
