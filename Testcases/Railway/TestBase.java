package Railway;

import Common.Utilities;
import Constant.Constant;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class TestBase {
 
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("Pre-condition");
		  System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + 
				  "\\Executables\\chromedriver.exe");
		  Constant.WEBDRIVER = new ChromeDriver();
		  Constant.WEBDRIVER.manage().window().maximize();
	  }

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("Post-condition");
		  Constant.WEBDRIVER.quit();
	  }

	
}
