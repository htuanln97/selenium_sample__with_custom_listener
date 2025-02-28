package Foody;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constant.Constant;
import core.driver_wrapper.DriverType;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;
public class TestBase {
	
	@Parameters({"broswer", "runningMode"})
	@BeforeMethod
	  public void beforeMethod(@Optional("Chrome") String browser, @Optional("Local") String runningMode, 
			  ITestContext context, Method method) {
		
		  ExtentReport.ExtentTestManager.startTest(method.getName(), ExtentReport.TestListener.testSuite.get(context.getName()));
		  
		  System.out.println("Pre-condition: Instantiate Webdriver");
		  DriverManagement.createDriver(DriverType.valueOf(browser), runningMode);
		  DriverManagement.getDriver().manage().window().maximize();
		  Driver.setImplicitlyWait(Constant.DEFAULT_TIMEOUT);
		  
	  }

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("Post-condition: Quit");
		  DriverManagement.getDriver().quit();
	  }

}
