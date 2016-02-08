package com.equal.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.equal.business.HomePageBO;
import com.equal.business.LoginBO;
import com.equal.common.Logger;
import com.equal.common.TestBase;
import com.equal.dataprovider.UserDP;
import com.equal.dto.UserDTO;
import com.equal.model.ApplicationLibrary;
import com.equal.model.CommonLibrary;
import com.equal.model.DataManager;
import com.equal.model.Reports;

public class Sample1 extends TestBase {

	String moduleName = "Sample Module One";
	String testCaseName = getClass().getSimpleName();

	CommonLibrary comlib = new CommonLibrary();
	ApplicationLibrary applib = new ApplicationLibrary();
	Reports reports = new Reports();
	DataManager datamanager = new DataManager();

	@BeforeClass
	public void startUp() throws Exception {
		comlib.initialize(testCaseName, datamanager, comlib, reports);
	}

	@BeforeMethod
	public void beforeTest(Method methodName) throws Exception {
		reports.writeTestName(methodName.getName());
	}

	@Test(dataProviderClass = UserDP.class, dataProvider = "firstUser")
	public void test(ITestContext context, UserDTO user) {
		try {
			LoginBO login = new LoginBO();
			HomePageBO home = new HomePageBO();
			Logger.logDebug("Login page -logging in");
			login.login(user);
			Logger.logDebug("Home page Verification");
			//this.asserter.assertPass(home.isHomePage(), "Home page is not present", "Home page is present");
			System.out.println("HIii");

			try {
				this.asserter.assertPass(home.isHomePage(), "Home page is not present", "Home page is present");
				reports.writeIntoFile(driver, testCaseName, "Validate Home Page", "Login with valid credentials",
						"Home page is displayed", reports.pass, "", comlib.getCurrentTime());
			} catch (Error e) {
				reports.writeIntoFile(driver, testCaseName, "Validate Home Page", "Login with valid credentials",
						"Home page is not displayed", reports.fail, e.getMessage(), comlib.getCurrentTime());
			}
		} catch (Exception e) {
			reports.writeIntoFile(driver, testCaseName, "Exception", "Tried performing action using webdriver",
					"Exception occured", reports.fail, e.getMessage(), comlib.getCurrentTime());
			Assert.fail("Exception occured please check logs");
		}
	}
	@AfterMethod
	public void afterTest() throws Exception {
		comlib.afterTestRun();
	}

	@AfterClass
	public void tearDown() throws Exception {
		comlib.quit(moduleName, testCaseName, datamanager, comlib, reports);
	}

}
