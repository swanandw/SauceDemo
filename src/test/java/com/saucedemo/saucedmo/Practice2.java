package com.saucedemo.saucedmo;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Practice2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
		
		String excelfilepath = "C:\\data\\UserData.xlsx";
		
		try {
			FileInputStream fis = new FileInputStream(excelfilepath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			int rows =sheet.getLastRowNum();
			for(int i =1;i<=rows;i++) {
				Row row =sheet.getRow(i);
				String username =row.getCell(0).getStringCellValue();
				String password =row.getCell(1).getStringCellValue();
				System.out.println("Username from Excel is:"+ username);
				System.out.println("Password from excel is:"+ password);
				driver.findElement(By.id("user-name")).sendKeys(username);
				driver.findElement(By.id("password")).sendKeys(password);
				driver.findElement(By.id("login-button")).click();
				driver.findElement(By.id("react-burger-menu-btn")).click();
				driver.findElement(By.id("logout_sidebar_link")).click();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String[] username =   {"standard_user","visual_user","problem_user","performance_glitch_user"};
//		String pwd = "secret_sauce";
//		for(int i =0;i<username.length;i++) {
//			driver.findElement(By.id("user-name")).sendKeys(username[i]);
//			driver.findElement(By.id("password")).sendKeys(pwd);
//			driver.findElement(By.id("login-button")).click();
//			driver.findElement(By.id("react-burger-menu-btn")).click();
//			driver.findElement(By.id("logout_sidebar_link")).click();
//		}
	}

}
