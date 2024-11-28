package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	private WebDriver driver;
	JavascriptExecutor js;
	
	//Locators
	
	private By selectFilterDrpdwn =By.className("product_sort_container");
	private By addProductToCard = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private By cartIcon = By.className("shopping_cart_link");
	private By cartValue  = By.xpath("//span[@class=\"shopping_cart_badge\"]");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.js= (JavascriptExecutor) driver;
	}
	
	public void applyFilter(String filterOption) {
		WebElement dropdown = driver.findElement(selectFilterDrpdwn);
		Select select = new Select(dropdown);
		select.selectByVisibleText(filterOption);
	}
	
	public void scrollDown() {
		js.executeScript("window.scrollBy(0,600);");
	}
	
	public void scrollUp() {
		js.executeScript("window.scrollBy(0,-600);");
	}
	
	
	public void addProductToCart() {
		driver.findElement(addProductToCard).click();
	}
	
	public void cartIconClick() {
		driver.findElement(cartIcon).click();
	}
	
	
	public String getCartValue() {
		return driver.findElement(cartValue).getText();
	}
}
