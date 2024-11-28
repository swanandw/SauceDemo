package com.saucedemo.saucedmo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	
	
	
	@BeforeTest
	public void setupReport() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report New");
		sparkReporter.config().setReportName("End to End Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Tester", "Swanand Walke");
		extent.setSystemInfo("Environment", "QA");
	}
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
	}
	
	@AfterClass
	public void closeBrowser() {
		if(driver!= null) {
			driver.quit();
		}
	}
	
	@AfterTest
	public void closeReports() {
		extent.flush();
	}
}
