package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinalCheckOutPage {

	protected WebDriver driver;
	
	public FinalCheckOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By totalPrice = By.xpath("//div[contains(text(), 'Total: $')]/parent::div//div[8]");
	By finishBtn = By.id("finish");
	By orderStatement = By.className("complete-header");
	
	public String veirfyTotalAmount() {
		return driver.findElement(totalPrice).getText();
	}
	
	public Double extectAmount() {
		String amountInString =driver.findElement(totalPrice).getText();
	amountInString=		amountInString.replace("Total: $", "");
	   Double valueInDouble =Double.parseDouble(amountInString);
	   return valueInDouble;
	
	}
	
	public String verifyFinalOrderStmt() {
		driver.findElement(finishBtn).click();
		return driver.findElement(orderStatement).getText();
	}
	
}
