package Tiki;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constant.Constant;
import ExtentReport.Logger;
import ExtentReport.TestListener;
import core.driver_wrapper.DriverType;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;

@Listeners(TestListener.class)
public class TestBase {

	@Parameters({ "broswer", "runningMode" })
	@BeforeMethod
	public void beforeMethod(@Optional("Chrome") String browser, @Optional("Local") String runningMode,
			ITestContext context, Method method) {
		System.out.println("Pre-condition: Instantiate Webdriver");
		DriverManagement.createDriver(DriverType.valueOf(browser), runningMode);
		DriverManagement.getDriver().manage().window().maximize();
		Driver.setImplicitlyWait(Constant.DISPOSE_TIME);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("Post-condition: Quit");
		DriverManagement.getDriver().quit();
	}

	// ================================================================================
	// Assert functions
	// ================================================================================
	public void assertTrue(boolean actualValue, String message) {
		try {
			Assert.assertTrue(actualValue, message);
			Logger.passedAssertion(message);
		} catch (AssertionError e) {
			Logger.fail(e.getMessage(), e);
//	            try {
//	                Logger.info("URL: " + DriverManagement.getDriver().getCurrentUrl());
//	            } catch (Throwable throwable) {
//	                Logger.info("URL: " + "Can't get Url");
//	            }
		}
	}

	public void assertFalse(boolean actualValue, String message) {
		try {
			Assert.assertFalse(actualValue, message);
			Logger.pass(message, 1);
		} catch (AssertionError e) {
			Logger.fail(e.getMessage(), e);
//	            try {
//	                Logger.info("URL: " + DriverManagement.getDriver().getCurrentUrl());
//	            } catch (Throwable throwable) {
//	                Logger.info("URL: " + "Can't get Url");
//	            }
		}
	}

	public void assertEquals(Object actualValue, Object expectedValue, String message) {
		try {
			Assert.assertEquals(actualValue, expectedValue, message);
			Logger.passedAssertion(message);
		} catch (AssertionError e) {
			Logger.fail(e.getMessage(), e);
//	            try {
//	                Logger.info("URL: " + DriverManagement.getDriver().getCurrentUrl());
//	            } catch (Throwable throwable) {
//	                Logger.info("URL: " + "Can't get Url");
//	            }
		}
	}

	public void assertNotEquals(Object actualValue, Object expectedValue, String message) {
		try {
			Assert.assertNotEquals(actualValue, expectedValue, message);
			Logger.passedAssertion(message);
		} catch (AssertionError e) {
			Logger.fail(e.getMessage(), e);
//	            try {
//	                Logger.info("URL: " + DriverManagement.getDriver().getCurrentUrl());
//	            } catch (Throwable throwable) {
//	                Logger.info("URL: " + "Can't get Url");
//	            }
		}
	}

}
