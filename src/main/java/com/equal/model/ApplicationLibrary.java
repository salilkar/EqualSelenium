package com.equal.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ApplicationLibrary {

	public void logout(CommonLibrary comlib, Reports reports, WebDriver driver, String testCaseName) throws InterruptedException
	{
		driver.findElement(By.id(TestData.btn_signout)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(TestData.txt_xp_logout)).click();
		
		try {
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.et_username)));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.et_password)));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.btn_login)));
			System.out.println("Login Page Validated");
			reports.writeIntoFile(driver, testCaseName, "Validate Logout button", "Click on logout", "Login page is displayed", reports.pass, "", comlib.getCurrentTime());
		} catch (Error e) {
			reports.writeIntoFile(driver, testCaseName, "Validate Logout button", "Click on logout", "Login page is not displayed", reports.fail, e.getMessage(), comlib.getCurrentTime());
			Assert.fail("Logout button validation failed");
		}
	}

	public void help(WebDriver driver)
	{
	driver.findElement(By.id(TestData.btn_welcome)).click();	
	String parentHandle = driver.getWindowHandle(); // get the current window handle
	driver.findElement(By.xpath(TestData.txt_xp_help)).click(); // click some link that opens a new window

	for (String winHandle : driver.getWindowHandles()) {
	    driver.switchTo().window(winHandle);
	}
	System.out.println("test");
 // close newly opened window when done with it
	driver.switchTo().window(parentHandle); 
	
	
	
	}
	/*
	 * Search For Member , Request , Program
	 * */
//	public void search(WebDriver driver, String searchlist, String SearchID) throws Exception {
//		
//		searchlist = searchlist.toLowerCase();
//		switch(searchlist) {
//
//		case "programs":
//			driver.findElement(By.id(Repository.btn_search)).click();
//			driver.findElement(By.xpath(Repository.txt_xp_program)).click();
//			driver.findElement(By.id(Repository.et_id_programid)).sendKeys(SearchID);
//			driver.findElement(By.id(Repository.btn_id_searchaerial)).click();
//			break;
//
//		case "requests":
//			driver.findElement(By.id(Repository.btn_search)).click();
//			driver.findElement(By.xpath(Repository.txt_xp_Request)).click();
//			driver.findElement(By.id(Repository.et_id_requestid)).sendKeys(SearchID);
//			driver.findElement(By.id(Repository.btn_id_searchrequest)).click();
//			break;
//			
//		case "members":
//			driver.findElement(By.id(Repository.btn_search)).click();
//			driver.findElement(By.xpath(Repository.txt_xp_memberid)).click();
//			driver.findElement(By.id(Repository.et_memberId)).sendKeys(SearchID);
//			driver.findElement(By.id(Repository.btn_memberIdSearch)).click();
//			
//			break;
//
//		default:
//			System.out.println("Incorrect  value");
//			break;
//		}
//	
//	}
	
	public void login(CommonLibrary comlib, Reports reports, WebDriver driver, String testCaseName ,String username , String password)
	{
		try {
			//comlib.ExplicitWait(driver, By.id(Repository.et_username));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.et_username)));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.et_password)));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.btn_login)));
			reports.writeIntoFile(driver, testCaseName, "Validate Login page", "Open browser with URL", "Login page is displayed", reports.pass, "", comlib.getCurrentTime());
		} catch (Error e) {
			reports.writeIntoFile(driver, testCaseName, "Validate Login page", "Open browser with URL", "Login page is not displayed", reports.fail, e.getMessage(), comlib.getCurrentTime());
			Assert.fail("Login page validation failed");
		}
		
		driver.findElement(By.id(TestData.et_username)).clear();
		driver.findElement(By.id(TestData.et_username)).sendKeys(username);
		driver.findElement(By.id(TestData.et_password)).clear();
		driver.findElement(By.id(TestData.et_password)).sendKeys(password);
		driver.findElement(By.id(TestData.btn_login)).click();
		
		try {
			comlib.ExplicitWait(driver, By.id(TestData.btn_signout));
			Assert.assertTrue(comlib.isElementPresent(driver, By.id(TestData.btn_signout)));
			reports.writeIntoFile(driver, testCaseName, "Validate Home page", "Login with valid credentials", "Home page is displayed", reports.pass, "", comlib.getCurrentTime());
		} catch (Exception e) {
			reports.writeIntoFile(driver, testCaseName, "Validate Home page", "Login with valid credentials", "Home page is not displayed", reports.fail, e.getMessage(), comlib.getCurrentTime());
			Assert.fail("Home page validation failed");
		}
		
	}
	
	
	public void unlock(CommonLibrary comlib,WebDriver driver) throws InterruptedException
	{
		if(comlib.isElementPresent(driver, By.xpath(TestData.prgNote_msg_xp_lockedMsg)))
		{
			System.out.println("Record is locked");
			Thread.sleep(3000);
			driver.findElement(By.xpath(TestData.prgNote_btn_xp_allItems)).click();			
		}
		else				
		{
			System.out.println("Record is not locked");
		}	
					
	}
	
	}
