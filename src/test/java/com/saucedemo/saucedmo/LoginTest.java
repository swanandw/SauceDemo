package com.saucedemo.saucedmo;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {
	



	private LoginPage loginpage;
	private HomePage homepage;
	private CartPage cartpage;
	private CheckOutPage checkoutpage;
	private FinalCheckOutPage finalcheckoutpage;
	
	@Test(dataProvider = "userData", priority = 1)
	public void loginLogoutTest(String username, String password) {
		test =extent.createTest("Login Test for: "+ username);
		loginpage = new LoginPage(driver);
		
		try {
			test.log(Status.INFO, "Starting login test for username: "+ username);
			loginpage.login(username, password);
			test.log(Status.PASS, " login successful for username: "+ username);
			Assert.assertTrue(loginpage.isLoginSuccessful(), "Login should be succeeded for user:" +username);
			test.log(Status.PASS, "Assrt verification successful for username: "+ username);
			loginpage.logout();
			test.log(Status.PASS, "Logout successful for username: "+ username);
			
		}
		catch(Exception e) {
			test.log(Status.FAIL, "Assrt verification failed for username: "+ username);
			test.log(Status.FAIL, e.getMessage());
			Assert.fail("Test failed for username: "+username);
		}
	}
		
	@Test(priority = 2)  
	public void verifyHomePage() {
		test =extent.createTest("Verify homePage");
		loginpage.login("standard_user", "secret_sauce");;
		homepage = new HomePage(driver);
		try {
		test.log(Status.INFO, "Starting home page test");
		homepage.applyFilter("Price (high to low)");
		test.log(Status.PASS, "Able to click on Filter");
		
		test.log(Status.INFO, "Starting scroll down");
		homepage.scrollDown();
		test.log(Status.PASS, "ScrollDown Successful");
		
		test.log(Status.INFO, "Click on Product");
		homepage.addProductToCart();
		test.log(Status.PASS, "Sucessfully added product");
		
		test.log(Status.INFO, "Starting scroll up");
		homepage.scrollUp();
		test.log(Status.PASS, "ScrollUP Successful");
		
		test.log(Status.INFO, "Click on cart icon");
		homepage.cartIconClick();
		test.log(Status.PASS, "Successful Clicked on cart icon");
		
		test.log(Status.INFO, "Verify added cart value");
		Assert.assertEquals(homepage.getCartValue(), "1");
		test.log(Status.PASS, "cart value verified");
		
	}catch(Exception e) {
		test.log(Status.FAIL, "Assrt verification failed ");
		test.log(Status.FAIL, e.getMessage());
		Assert.fail("Test failed for Homepage");
	}
	}	
	
	@Test(priority = 3)
	public void verifyCartPageDetails() {
		test =extent.createTest("Verify Cart Page");
		cartpage = new CartPage(driver);
		
		try {
			test.log(Status.INFO, "Verify Product Name");
			Assert.assertEquals(cartpage.verifyProductName(), "Sauce Labs Bolt T-Shirt");
			test.log(Status.PASS, "Verified Product Name");
		
			test.log(Status.INFO, "Verify Product Price");
			Assert.assertEquals(cartpage.verifyProductPrice(), 15.99);
			test.log(Status.PASS, "Verified Product Price");
			
			test.log(Status.INFO, "Click on Checkout");
			cartpage.clickOnCheckOutButton();
			test.log(Status.PASS, "Successfully clicked on Checkout button");
			
		}catch(Exception e) {
			test.log(Status.FAIL, "Assrt verification failed ");
			test.log(Status.FAIL, e.getMessage());
			Assert.fail("Test failed for CartPage");
		}
	}
	
	@Test(priority = 4)
	public void verifyCheckOutPage() {
		test = extent.createTest("Verify CheckOut Page");
		checkoutpage = new CheckOutPage(driver);
		String firstName = "Swanand";
		String lastName = "Walke";
		String zipCode = "411001";
		
		try {
			
			
			test.log(Status.INFO, "Verify CheckOut Page Text");
			Assert.assertEquals(checkoutpage.verifyCheckOutPageText(), "Checkout: Your Information");
			test.log(Status.PASS, "Verified Text");
			
			test.log(Status.INFO, "Enter the User Details and click on Continue");
			checkoutpage.enterUserDetails(firstName, lastName, zipCode);
			test.log(Status.PASS, "Entered the User Details successfully and Clicked on continue");
			
		}catch(Exception e) {
			test.log(Status.FAIL, "Assrt verification failed ");
			test.log(Status.FAIL, e.getMessage());
			Assert.fail("Test failed for CheckOutPage");
		}
	}
	
  @Test(priority = 5)
  public void verifyFinalCheckOutPage() {
	  test = extent.createTest("Verify Final CheckOut Page");
	  finalcheckoutpage = new FinalCheckOutPage(driver);
	  
	  try {
		  test.log(Status.INFO, "Verify Total amount");
		  String totalValue = finalcheckoutpage.veirfyTotalAmount();
		  System.out.println(totalValue);
		  Assert.assertEquals(finalcheckoutpage.veirfyTotalAmount(), "Total: $17.27");
		  test.log(Status.PASS, "Total amount verified");
		  
		  test.log(Status.INFO, "Verify Extracted total amount only");
		  Double test1 =finalcheckoutpage.extectAmount();
		  System.out.println(test1);
		  Assert.assertEquals(finalcheckoutpage.extectAmount(), 17.27);
		  test.log(Status.PASS, "Total Extracted Amount Verified Correctly");
		  
		  test.log(Status.INFO, "Verify final message of End to End Testing");
		  Assert.assertEquals(finalcheckoutpage.verifyFinalOrderStmt(), "Thank you for your order!");
		  test.log(Status.PASS, "Complete End To End Testing is done successfully!");
		  
	  }catch(Exception e) {
			test.log(Status.FAIL, "Assrt verification failed ");
			test.log(Status.FAIL, e.getMessage());
			Assert.fail("Test failed for Final CheckOutPage");
		}
  }
	
	@DataProvider(name ="userData")
	public Object[][] getUserData(){
		//String excelPath = "C:\\data\\UserData.xlsx";
		//String excelPath = "\\saucedemo\\data\\UserData.xlsx";
		String excelPath = "data/UserData.xlsx";
		Object[][] data = null;
		
		try {
			FileInputStream fis = new FileInputStream(excelPath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet =workbook.getSheetAt(0);
			int rows =sheet.getLastRowNum();
			data = new Object[rows][2];
			for(int i =1;i<=rows;i++) {
				Row row =sheet.getRow(i);
				data[i-1][0]=row.getCell(0).getStringCellValue();
				data[i-1][1]=row.getCell(1).getStringCellValue();
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
}
