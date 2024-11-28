package com.saucedemo.saucedmo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		
		String[] username =   {"standard_user","visual_user","problem_user","performance_glitch_user"};
		String pwd = "secret_sauce";
		for(int i =0;i<username.length;i++) {
			driver.findElement(By.id("user-name")).sendKeys(username[i]);
			driver.findElement(By.id("password")).sendKeys(pwd);
			driver.findElement(By.id("login-button")).click();
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("logout_sidebar_link")).click();
		}
	}

}
