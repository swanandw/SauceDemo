package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	
	//Locators
	
	private By usernameField =By.id("user-name");
	private By passwordField = By.id("password");
	private By loginBtn = By.id("login-button");
	private By menuBtn  = By.id("react-burger-menu-btn");
	private By logoutBtn = By.id("logout_sidebar_link");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void login(String username, String password) {
		driver.findElement(usernameField).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginBtn).click();
	}
	
	public void logout() {
		driver.findElement(menuBtn).click();
		driver.findElement(logoutBtn).click();
	}
	
	public boolean isLoginSuccessful() {
		return driver.getCurrentUrl().contains("inventory.html");
	}
}
