package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	
	protected WebDriver driver;
	
	By productName = By.className("inventory_item_name");
	By productPrice = By.className("inventory_item_price");
	By checkoutBtn = By.id("checkout");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String verifyProductName() {
		return driver.findElement(productName).getText();
	}
	
	public Double verifyProductPrice() {
		String value= driver.findElement(productPrice).getText();
		       value =value.replace("$", "");
	           Double amount =Double.parseDouble(value);
	           return amount;
	}
	
public void clickOnCheckOutButton() {
	driver.findElement(checkoutBtn).click();
	
	}
}
